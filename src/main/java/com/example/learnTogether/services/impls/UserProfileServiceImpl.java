package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.request.UserProfileSearchCriteria;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.dto.UserProfileDto;
import com.example.learnTogether.models.UserProfile;
import com.example.learnTogether.repository.UserProfileRepository;
import com.example.learnTogether.models.Authority;
import com.example.learnTogether.models.User;
import com.example.learnTogether.repository.AuthorityRepository;
import com.example.learnTogether.repository.UserRepository;
import com.example.learnTogether.services.UserProfileService;
import com.example.learnTogether.specifications.UserProfileSearchSpecifications;
import com.example.learnTogether.utils.Defs;
import com.example.learnTogether.utils.Role;
import com.example.learnTogether.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Qualifier("userPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserProfileServiceImpl(
            UserProfileRepository userProfileRepository,
            UserRepository userRepository,
            AuthorityRepository authorityRepository
    ){
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    private User addOauthUser(UserProfile employee, String password) throws GenericException {
        try {
            Optional<User> optionalUser = userRepository.findByUsername(employee.getEmail());
            if (optionalUser.isPresent()) {
                logger.error("User already exists, email: {}", employee.getEmail());
                throw new GenericException(Defs.USER_ALREADY_EXISTS);
            }

            User user = new User();
            user.setUsername(employee.getEmail());
            user.setEnabled(true);

            Authority authority = authorityRepository.findByName(Role.EMPLOYEE.getValue());
            user.setAuthorities(Arrays.asList(authority));
            user.setPassword(passwordEncoder.encode(password));

            userRepository.save(user);
            return user;
        }catch (GenericException e){
            throw e;
        }catch (Exception e){
            logger.error("Error occurred while creating oauth user!");
            throw new GenericException("Error occurred while creating oauth user!", e);
        }
    }
    @Override
    public ServiceResponse<UserProfileDto> userSignUp(UserProfileDto customUserDto) throws GenericException {
        try {
            Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUserId(customUserDto.getUserId());
            if (optionalUserProfile.isPresent()) throw new GenericException(Defs.USER_ALREADY_EXISTS);

            UserProfile employee = new UserProfile();
            Utils.copyProperty(customUserDto, employee);

            User user = addOauthUser(employee, customUserDto.getPassword());
            employee.setOauthUser(user);
            employee.setStatus(true);

            employee.setCreateTime(LocalDateTime.now());

            employee = userProfileRepository.save(employee);


            Utils.copyProperty(employee, customUserDto);
            return new ServiceResponse(Utils.getSuccessResponse(), customUserDto);
        }catch (GenericException e){
            throw e;
        }catch (Exception e){
            logger.error("Exception occurred while enrolling employee, message: {}", e.getMessage());
            throw new GenericException(e.getMessage(), e);
        }
    }



    @Override
    public UserProfileDto findByUsername(String username) throws GenericException{

        try {
            Optional<UserProfile> optionalEmployee = userProfileRepository.findByEmail(username);
            if (!optionalEmployee.isPresent() || optionalEmployee.get().getStatus().equals(false)) {
                return null;
            }
            UserProfileDto customUserDto = new UserProfileDto();
            Utils.copyProperty(optionalEmployee.get(), customUserDto);
            return customUserDto;
        }catch (Exception e){
            logger.error("Error while finding employee by username: {}", username);
            throw new GenericException(e.getMessage());
        }
    }
    @Override
    public ServiceResponse<UserProfileDto> findEmployeeById(Long id) throws GenericException{
        try {
            Optional<UserProfile> optionalUser = userProfileRepository.findById(id);

            if (!optionalUser.isPresent() || optionalUser.get().getStatus().equals(false)) {
                return new ServiceResponse<>(Utils.getSingleErrorBadRequest(
                        new ArrayList<>(),
                        "employeeId", Defs.EMPLOYEE_NOT_FOUND,
                        "Please check employee id is correct"), null);
            } else {
                UserProfileDto customUserDto = new UserProfileDto();
                Utils.copyProperty(optionalUser.get(), customUserDto);
                return new ServiceResponse(Utils.getSuccessResponse(), customUserDto);
            }
        }catch (Exception e){
            logger.error("Error occurred while fetching employee by id: {}", id);
            throw new GenericException("Error occurred while fetching employee by id", e);
        }
    }

    @Override
    public ServiceResponse<UserProfileDto> updateEmployeeById(Long id, UserProfileDto customUserDto) throws GenericException{
        try {
            Optional<UserProfile> loggedInEmployee = userProfileRepository.getLoggedInEmployee();
            if (loggedInEmployee.isPresent() && !loggedInEmployee.get().getId().equals(id)) {
                return new ServiceResponse<>(Utils.getSingleErrorBadRequest(
                        new ArrayList<>(),
                        null, Defs.NO_PERMISSION_TO_DELETE,
                        "Please check you have permission to do this operation!"), null);
            }

            Optional<UserProfile> optionalEmployee = userProfileRepository.findById(id);
            if (!optionalEmployee.isPresent() || optionalEmployee.get().getStatus().equals(false)){
                return new ServiceResponse<>(Utils.getSingleErrorBadRequest(
                        new ArrayList<>(),
                        "employeeId", Defs.EMPLOYEE_NOT_FOUND,
                        "Please check employee id is correct"), null);
            }


            UserProfile employee = optionalEmployee.get();
            if (!Utils.isNullOrEmpty(customUserDto.getFirstName())) {
                employee.setFirstName(customUserDto.getFirstName());
            }
            if (!Utils.isNullOrEmpty(customUserDto.getLastName())) {
                employee.setLastName(customUserDto.getLastName());
            }
            employee = userProfileRepository.save(employee);

            Utils.copyProperty(employee, customUserDto);

            return new ServiceResponse(Utils.getSuccessResponse(), customUserDto);
        }catch (Exception e){
         logger.error("Exception occurred while updating employee, id: {}", id);
         throw new GenericException(e.getMessage(), e);
        }
    }

    @Override
    public Page<UserProfile> getEmployeeList(UserProfileSearchCriteria criteria, @PageableDefault(value = 10) Pageable pageable) throws GenericException{
        Optional<UserProfile> loggedInEmployee = userProfileRepository.getLoggedInEmployee();
        Long id = null;
        if(loggedInEmployee.isPresent()){
            id = loggedInEmployee.get().getId();
        }

        Page<UserProfile> userPage = userProfileRepository.findAll(
                UserProfileSearchSpecifications.withId(id==null ? criteria.getId() : id)
                        .and(UserProfileSearchSpecifications.withFirstName(criteria.getFirstName()))
                        .and(UserProfileSearchSpecifications.withLastName(criteria.getLastName()))
                        .and(UserProfileSearchSpecifications.withEmail(criteria.getEmail()))
                        .and(UserProfileSearchSpecifications.withPhone(criteria.getPhone()))
                        .and(UserProfileSearchSpecifications.withStatus(true))
                ,pageable
        );
        return userPage;
    }

    @Override
    public ServiceResponse<Boolean> deleteEmployeeById(Long id) throws GenericException{
        try {
            Optional<UserProfile> loggedInEmployee = userProfileRepository.getLoggedInEmployee();
            Optional<UserProfile> optionalEmployee = userProfileRepository.findById(id);
            if (loggedInEmployee.isPresent() && optionalEmployee.isPresent() &&
                    !loggedInEmployee.get().getId().equals(optionalEmployee.get().getId())) {

                return new ServiceResponse<>(Utils.getSingleErrorBadRequest(
                        new ArrayList<>(),
                        null, Defs.NO_PERMISSION_TO_DELETE,
                        "Please check you have permission to do this operation!"), null);
            }
            if (!optionalEmployee.isPresent()) {
                return new ServiceResponse<>(Utils.getSingleErrorBadRequest(
                        new ArrayList<>(),
                        "employeeId", Defs.EMPLOYEE_NOT_FOUND,
                        "Please check employee id is correct"), null);
            }

            UserProfile employee = optionalEmployee.get();
            employee.setStatus(false);
            try {
                employee = userProfileRepository.save(employee);
                User user = employee.getOauthUser();
                user.setEnabled(false);
                userRepository.save(user);
            } catch (Exception e) {
                logger.error(Defs.EXCEPTION_OCCURRED_WHILE_SAVING_USER_INFO+", message: {}", e.getMessage());
                throw new GenericException(Defs.EXCEPTION_OCCURRED_WHILE_SAVING_USER_INFO, e);
            }
            return new ServiceResponse(Utils.getSuccessResponse(), true);
        } catch (GenericException e){
            throw e;
        }catch (Exception e){
            logger.error("Exception occurred while deleting user, user id: {}", id);
            throw new GenericException(e.getMessage(), e);
        }
    }
}
