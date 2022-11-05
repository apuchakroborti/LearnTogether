package com.example.learnTogether.services;

import com.example.learnTogether.dto.request.UserProfileSearchCriteria;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.dto.UserProfileDto;
import com.example.learnTogether.models.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserProfileService {
    ServiceResponse<UserProfileDto> userSignUp(UserProfileDto user) throws GenericException;
    UserProfileDto findByUsername(String username) throws GenericException;
    ServiceResponse<UserProfileDto> findEmployeeById(Long id) throws GenericException;
    ServiceResponse<UserProfileDto> updateEmployeeById(Long id, UserProfileDto employeeBean) throws GenericException;
    Page<UserProfile> getEmployeeList(UserProfileSearchCriteria criteria, Pageable pageable) throws GenericException;
    ServiceResponse<Boolean>  deleteEmployeeById(Long id) throws GenericException;
}
