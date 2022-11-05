package com.example.learnTogether.services;


import com.example.learnTogether.dto.UserProfileDto;
import com.example.learnTogether.dto.request.LoginRequestDto;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;

public interface LoginService {
    ServiceResponse<UserProfileDto> checkLoginUser(LoginRequestDto loginRequestDto) throws GenericException;

}
