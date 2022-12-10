package com.apu.example.learnTogether.controllers;

import com.apu.example.learnTogether.dto.UserProfileDto;
import com.apu.example.learnTogether.dto.request.LoginRequestDto;
import com.apu.example.learnTogether.dto.response.ServiceResponse;
import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    /*
    * this API is for checking the user is valid by username and password
    * @response: employee details
    * */
    @PostMapping
    public ServiceResponse<UserProfileDto> checkLoginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) throws GenericException {
        return loginService.checkLoginUser(loginRequestDto);
    }
}
