package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.request.PasswordChangeRequestDto;
import com.example.learnTogether.dto.request.PasswordResetRequestDto;
import com.example.learnTogether.dto.request.UserProfileSearchCriteria;
import com.example.learnTogether.dto.response.Pagination;
import com.example.learnTogether.dto.response.PasswordChangeResponseDto;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.dto.UserProfileDto;
import com.example.learnTogether.models.UserProfile;
import com.example.learnTogether.security_oauth2.services.UserService;
import com.example.learnTogether.services.UserProfileService;
import com.example.learnTogether.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {

    private final UserProfileService customUserService;
    private final UserService userService;

    @Autowired
    UserProfileController(UserProfileService customUserService,
                          UserService userService){
        this.customUserService = customUserService;
        this.userService = userService;
    }

    @PostMapping
    public ServiceResponse<UserProfileDto> signUpUser(@Valid @RequestBody UserProfileDto userProfileDto) throws GenericException {
        return customUserService.userSignUp(userProfileDto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public ServiceResponse<Page<UserProfileDto>> searchEmployee(UserProfileSearchCriteria criteria, @PageableDefault(value = 10) Pageable pageable) throws GenericException {
        Page<UserProfile>  employeePage = customUserService.getEmployeeList(criteria, pageable);

        return new ServiceResponse(Utils.getSuccessResponse(),
                Utils.toDtoList(employeePage, UserProfileDto.class),
                new Pagination(employeePage.getTotalElements(), employeePage.getNumberOfElements(), employeePage.getNumber(), employeePage.getSize()));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping(path = "/{id}")
    public ServiceResponse<UserProfileDto> getEmployeeById(@PathVariable(name = "id") Long id ) throws GenericException {
        return customUserService.findEmployeeById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public ServiceResponse<UserProfileDto> updateEmployeeById(@PathVariable(name = "id") Long id, @RequestBody UserProfileDto employeeBean) throws GenericException {
        return customUserService.updateEmployeeById(id, employeeBean);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public ServiceResponse<Boolean> deleteEmployeeById(@PathVariable(name = "id") Long id) throws GenericException {
        return customUserService.deleteEmployeeById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/update-password")
    public ServiceResponse<PasswordChangeResponseDto> updatePassword(@RequestBody PasswordChangeRequestDto passwordChangeRequestDto) throws GenericException {
        return userService.changeUserPassword(passwordChangeRequestDto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/reset-password")
    public ServiceResponse<PasswordChangeResponseDto> resetPassword(@RequestBody PasswordResetRequestDto passwordResetRequestDto) throws GenericException {
        return userService.resetPassword(passwordResetRequestDto);
    }
}
