package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.UserProfileCreateUpdateDto;
import com.example.learnTogether.dto.request.UserProfileSearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.dto.UserProfileDto;
import com.example.learnTogether.models.*;
import com.example.learnTogether.repository.*;
import com.example.learnTogether.services.UserProfileService;
import com.example.learnTogether.specifications.UserProfileSearchSpecifications;
import com.example.learnTogether.utils.Defs;
import com.example.learnTogether.utils.Role;
import com.example.learnTogether.utils.Utils;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

@Service
//@Transactional
@Slf4j
//@EnableTransactionManagement
public class UserProfileServiceImpl implements UserProfileService {

    Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    private final HazelcastInstance hazelcastInstance;
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final ProfessionRepository professionRepository;
    private final DistrictRepository districtRepository;
    private final CountryRepository countryRepository;

    @Qualifier("userPasswordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserProfileServiceImpl(
            UserProfileRepository userProfileRepository,
            UserRepository userRepository,
            AuthorityRepository authorityRepository,
            ProfessionRepository professionRepository,
            DistrictRepository districtRepository,
            CountryRepository countryRepository,
            @Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance
    ){
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.professionRepository = professionRepository;
        this.districtRepository = districtRepository;
        this.countryRepository = countryRepository;
        this.hazelcastInstance = hazelcastInstance;
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
    @Transactional
    public UserProfileDto userSignUp(UserProfileCreateUpdateDto profileCreateUpdateDto) throws GenericException {
        try {

            UserProfile userProfile = new UserProfile();
            Utils.copyProperty(profileCreateUpdateDto, userProfile);
            //set Profession
            if(profileCreateUpdateDto.getProfessionId()!=null){
                Optional<Profession> optionalProfession = professionRepository.findById(profileCreateUpdateDto.getProfessionId());
                if(optionalProfession.isPresent()){
                    userProfile.setProfession(optionalProfession.get());
                }
            }else{
                userProfile.setProfession(null);
            }

            //setDistrict
            if(profileCreateUpdateDto.getDistrictId()!=null){
                Optional<District> optionalDistrict = districtRepository.findById(profileCreateUpdateDto.getDistrictId());
                if(optionalDistrict.isPresent()){
                    userProfile.setDistrict(optionalDistrict.get());
                }
            }else{
                userProfile.setDistrict(null);
            }

            //setCountry
            if(profileCreateUpdateDto.getCountryId()!=null){
                Optional<Country> optionalCountry = countryRepository.findById(profileCreateUpdateDto.getCountryId());
                if(optionalCountry.isPresent()){
                    userProfile.getDistrict().setCountry(optionalCountry.get());
                }
            }else{
                userProfile.getDistrict().setCountry(null);
            }

            //generate and set userId
            Long currentId = userProfileRepository.getMaxId();
            userProfile.setUserId("USER"+(currentId+1));


            User user = addOauthUser(userProfile, profileCreateUpdateDto.getPassword());
            userProfile.setOauthUser(user);
            userProfile.setStatus(true);

            userProfile.setCreateTime(LocalDateTime.now());

            userProfile = userProfileRepository.save(userProfile);

            UserProfileDto userProfileDto = new UserProfileDto();
            Utils.copyProperty(userProfile, userProfileDto);
            return userProfileDto;
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
//    @CacheEvict(value = "userCache", key = "#id", beforeInvocation = true)
    @Cacheable(value = "userCache", key = "#id", unless = "#result==null")
//    @Cacheable(value = "userCache", key = "#id")
    @Transactional
    public UserProfileDto findUserProfileById(Long id) throws GenericException{

        log.info("search user findUserProfileById service start...");

        try {
            Optional<UserProfile> optionalUser = userProfileRepository.findById(id);

            if (!optionalUser.isPresent() || optionalUser.get().getStatus().equals(false)) {
                throw  new GenericException(Defs.EMPLOYEE_NOT_FOUND);
            } else {
                UserProfileDto customUserDto = new UserProfileDto();
                Utils.copyProperty(optionalUser.get(), customUserDto);
                log.info("search user findUserProfileById service end");
                return customUserDto;
            }
        }catch (Exception e){
            logger.error("Error occurred while fetching employee by id: {}", id);
            throw new GenericException("Error occurred while fetching employee by id", e);
        }
    }

    @Override
    public UserProfileDto updateEmployeeById(Long id, UserProfileDto customUserDto) throws GenericException{
        try {
            Optional<UserProfile> loggedInEmployee = userProfileRepository.getLoggedInEmployee();
            if (loggedInEmployee.isPresent() && !loggedInEmployee.get().getId().equals(id)) {
                throw new GenericException(Defs.NO_PERMISSION_TO_UPDATE);
            }

            Optional<UserProfile> optionalEmployee = userProfileRepository.findById(id);
            if (!optionalEmployee.isPresent() || optionalEmployee.get().getStatus().equals(false)){
                throw new GenericException(Defs.EMPLOYEE_NOT_FOUND);
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

            return customUserDto;
        }catch (Exception e){
         logger.error("Exception occurred while updating employee, id: {}", id);
         throw new GenericException(e.getMessage(), e);
        }
    }


    @Override
    @Cacheable(cacheNames = { "userCache" })
    @Transactional
    public Page<UserProfileDto> getEmployeeList(UserProfileSearchCriteria criteria, @PageableDefault(value = 10) Pageable pageable) throws GenericException{
        log.info("search user profile service start...");

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


        Page<UserProfileDto> userProfileDtoList = userPage.map(new Function<UserProfile, UserProfileDto>() {
            @Override
            public UserProfileDto apply(UserProfile userProfile) {
                UserProfileDto userProfileDto =  Utils.convertClass(userProfile, UserProfileDto.class);
                return userProfileDto;
            }
        });


        log.info("search user profile service end");
        return userProfileDtoList;
    }

    @CacheEvict(value = "userCache", key = "#id")
    @Override
    @Transactional
    public Boolean deleteEmployeeById(Long id) throws GenericException{
        try {
            Optional<UserProfile> loggedInEmployee = userProfileRepository.getLoggedInEmployee();
            Optional<UserProfile> optionalEmployee = userProfileRepository.findById(id);
            if (loggedInEmployee.isPresent() && optionalEmployee.isPresent() &&
                    !loggedInEmployee.get().getId().equals(optionalEmployee.get().getId())) {

                throw  new GenericException(Defs.NO_PERMISSION_TO_DELETE);
            }
            if (!optionalEmployee.isPresent()) {
               throw new GenericException(Defs.EMPLOYEE_NOT_FOUND);
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
            return true;
        } catch (GenericException e){
            throw e;
        }catch (Exception e){
            logger.error("Exception occurred while deleting user, user id: {}", id);
            throw new GenericException(e.getMessage(), e);
        }
    }
}
