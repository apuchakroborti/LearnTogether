package com.example.learnTogether.security_oauth2.services;


import com.example.learnTogether.dto.request.PasswordChangeRequestDto;
import com.example.learnTogether.dto.request.PasswordResetRequestDto;
import com.example.learnTogether.dto.response.PasswordChangeResponseDto;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;

public interface UserService {
    ServiceResponse<PasswordChangeResponseDto> changeUserPassword(PasswordChangeRequestDto passwordChangeRequestDto) throws GenericException;
    ServiceResponse<PasswordChangeResponseDto> resetPassword(PasswordResetRequestDto passwordResetRequestDto) throws GenericException;
}