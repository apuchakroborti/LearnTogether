package com.apu.example.learnTogether.security_oauth2.services;


import com.apu.example.learnTogether.dto.request.PasswordChangeRequestDto;
import com.apu.example.learnTogether.dto.request.PasswordResetRequestDto;
import com.apu.example.learnTogether.dto.response.PasswordChangeResponseDto;
import com.apu.example.learnTogether.dto.response.ServiceResponse;
import com.apu.example.learnTogether.exceptions.GenericException;

public interface UserService {
    ServiceResponse<PasswordChangeResponseDto> changeUserPassword(PasswordChangeRequestDto passwordChangeRequestDto) throws GenericException;
    ServiceResponse<PasswordChangeResponseDto> resetPassword(PasswordResetRequestDto passwordResetRequestDto) throws GenericException;
}