package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.UserProfileDto;
import com.example.learnTogether.dto.request.LoginRequestDto;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.services.LoginService;
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
