package com.example.learnTogether.services;

import com.example.learnTogether.dto.UserProfileCreateUpdateDto;
import com.example.learnTogether.dto.request.UserProfileSearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.dto.UserProfileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserProfileService {
    UserProfileDto userSignUp(UserProfileCreateUpdateDto user) throws GenericException;
    UserProfileDto findByUsername(String username) throws GenericException;
    UserProfileDto findUserProfileById(Long id) throws GenericException;
    UserProfileDto updateEmployeeById(Long id, UserProfileDto employeeBean) throws GenericException;
    Page<UserProfileDto> getEmployeeList(UserProfileSearchCriteria criteria, Pageable pageable) throws GenericException;
    Boolean  deleteEmployeeById(Long id) throws GenericException;
}
