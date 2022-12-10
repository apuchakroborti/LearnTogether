package com.apu.example.learnTogether.services.impls;

import com.apu.example.learnTogether.dto.UserProfileDto;
import com.apu.example.learnTogether.dto.request.LoginRequestDto;
import com.apu.example.learnTogether.dto.response.ServiceResponse;
import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.models.UserProfile;
import com.apu.example.learnTogether.repository.UserProfileRepository;
import com.apu.example.learnTogether.models.User;
import com.apu.example.learnTogether.repository.UserRepository;
import com.apu.example.learnTogether.services.LoginService;
import com.apu.example.learnTogether.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
