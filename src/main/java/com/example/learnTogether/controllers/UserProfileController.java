package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.APIResponse;
import com.example.learnTogether.dto.Pagination;
import com.example.learnTogether.dto.UserProfileCreateUpdateDto;
import com.example.learnTogether.dto.request.PasswordChangeRequestDto;
import com.example.learnTogether.dto.request.PasswordResetRequestDto;
import com.example.learnTogether.dto.request.UserProfileSearchCriteria;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
    public ResponseEntity<APIResponse> searchEmployee(UserProfileSearchCriteria criteria, @PageableDefault(value = 10) Pageable pageable) throws GenericException {

        Page<UserProfileDto> userProfileDtoPage = customUserService.getUserList(criteria, pageable);

        APIResponse<List<UserProfileDto>> responseDTO = APIResponse
                .<List<UserProfileDto>>builder()
                .status("SUCCESS")
                .results(userProfileDtoPage.getContent())
                .pagination(new Pagination(userProfileDtoPage.getTotalElements(), userProfileDtoPage.getNumberOfElements(), userProfileDtoPage.getNumber(), userProfileDtoPage.getSize()))
                .build();
        return new ResponseEntity<APIResponse>(responseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping(path = "/{id}")
    public ServiceResponse<UserProfileDto> getUserProfileById(@PathVariable(name = "id") Long id ) throws GenericException {
        UserProfileDto userProfileDto = customUserService.findUserProfileById(id);
        return new ServiceResponse<>(null, userProfileDto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public ServiceResponse<UserProfileDto> updateEmployeeById(@PathVariable(name = "id") Long id, @RequestBody UserProfileCreateUpdateDto createUpdateDto) throws GenericException {
        return new ServiceResponse(null, customUserService.updateEmployeeById(id, createUpdateDto));
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
