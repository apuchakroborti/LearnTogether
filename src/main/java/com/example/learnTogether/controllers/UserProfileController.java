package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.UserProfileCreateUpdateDto;
import com.example.learnTogether.dto.request.PasswordChangeRequestDto;
import com.example.learnTogether.dto.request.PasswordResetRequestDto;
import com.example.learnTogether.dto.request.UserProfileSearchCriteria;
import com.example.learnTogether.dto.response.Pagination;
import com.example.learnTogether.dto.response.PasswordChangeResponseDto;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.dto.UserProfileDto;
import com.example.learnTogether.security_oauth2.services.UserService;
import com.example.learnTogether.services.UserProfileService;
import com.example.learnTogether.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/user-profile")
@Slf4j
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
    public ServiceResponse<UserProfileDto> signUpUser(@Valid @RequestBody UserProfileCreateUpdateDto userProfileDto) throws GenericException {
        return new ServiceResponse<>(null, customUserService.userSignUp(userProfileDto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public ServiceResponse<Page<UserProfileDto>> searchEmployee(UserProfileSearchCriteria criteria, @PageableDefault(value = 10) Pageable pageable) throws GenericException {
        log.info("search user profile start...");

        Page<UserProfileDto> userProfileDtoList = customUserService.getEmployeeList(criteria, pageable);

        log.info("search user profile end");
        return new ServiceResponse(Utils.getSuccessResponse(),
                userProfileDtoList.getContent(),
                new Pagination(userProfileDtoList.getTotalElements(),
                        userProfileDtoList.getNumberOfElements(),
                        userProfileDtoList.getNumber(),
                        userProfileDtoList.getSize()));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping(path = "/{id}")
    public ServiceResponse<UserProfileDto> getUserProfileById(@PathVariable(name = "id") Long id ) throws GenericException {
        log.info("search user getUserProfileById start...");
        UserProfileDto userProfileDto = customUserService.findUserProfileById(id);
        log.info("search user getUserProfileById end");
        return new ServiceResponse<>(null, userProfileDto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public ServiceResponse<UserProfileDto> updateEmployeeById(@PathVariable(name = "id") Long id, @RequestBody UserProfileDto employeeBean) throws GenericException {
        return new ServiceResponse(null, customUserService.updateEmployeeById(id, employeeBean));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public ServiceResponse<Boolean> deleteEmployeeById(@PathVariable(name = "id") Long id) throws GenericException {
        return new ServiceResponse(null, customUserService.deleteEmployeeById(id));
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
