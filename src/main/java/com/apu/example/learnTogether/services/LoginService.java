package com.apu.example.learnTogether.services;


import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.UserProfileDto;
import com.apu.example.learnTogether.dto.request.LoginRequestDto;
import com.apu.example.learnTogether.dto.response.ServiceResponse;

public interface LoginService {
    ServiceResponse<UserProfileDto> checkLoginUser(LoginRequestDto loginRequestDto) throws GenericException;

}
