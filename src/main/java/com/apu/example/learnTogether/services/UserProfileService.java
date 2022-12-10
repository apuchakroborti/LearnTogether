package com.apu.example.learnTogether.services;

import com.apu.example.learnTogether.dto.UserProfileCreateUpdateDto;
import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.request.UserProfileSearchCriteria;
import com.apu.example.learnTogether.dto.UserProfileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserProfileService {
    UserProfileDto userSignUp(UserProfileCreateUpdateDto user) throws GenericException;
    UserProfileDto findByUsername(String username) throws GenericException;
    UserProfileDto findUserProfileById(Long id) throws GenericException;
    UserProfileDto updateEmployeeById(Long id, UserProfileCreateUpdateDto createUpdateDto) throws GenericException;
    Page<UserProfileDto> getUserList(UserProfileSearchCriteria criteria, Pageable pageable) throws GenericException;
    Boolean  deleteEmployeeById(Long id) throws GenericException;
}
