package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.UserProfileDto;
import com.example.learnTogether.dto.request.LoginRequestDto;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.UserProfile;
import com.example.learnTogether.repository.UserProfileRepository;
import com.example.learnTogether.models.User;
import com.example.learnTogether.repository.UserRepository;
import com.example.learnTogether.services.LoginService;
import com.example.learnTogether.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    UserProfileRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Qualifier("userPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public ServiceResponse<UserProfileDto> checkLoginUser(LoginRequestDto loginRequestDto) throws GenericException {
        try {
            UserProfileDto customUserDto = new UserProfileDto();

            Optional<User> optionalUser = userRepository.findByUsername(loginRequestDto.getUsername());
            if (optionalUser.isPresent()) {
                if (passwordEncoder.matches(loginRequestDto.getPassword(), optionalUser.get().getPassword())) {
                    Optional<UserProfile> optionalEmployee = employeeRepository.findByEmail(loginRequestDto.getUsername());
                    if (optionalEmployee.isPresent()) {
                        Utils.copyProperty(optionalEmployee.get(), customUserDto);
                    } else {
                        customUserDto.setFirstName("Admin");
                        customUserDto.setLastName("Admin");
                    }
                } else {
                   throw new GenericException("Please check username or password is incorrect");
                }
            } else {
                throw new GenericException("Please check username or password is incorrect");
            }
            return new ServiceResponse(Utils.getSuccessResponse(), customUserDto);
        }catch (Exception e){
            logger.error("Exception occurred while checking login user, message: {}", e.getMessage());
            throw new GenericException(e.getMessage(), e);
        }
    }
}
