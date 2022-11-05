package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.UserGroupDto;
import com.example.learnTogether.dto.request.UserGroupSearchCriteria;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.services.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/user-group")
public class UserGroupController {
    private final UserGroupService userGroupService;

    @Autowired
    UserGroupController(UserGroupService userGroupService){
        this.userGroupService = userGroupService;
    }

    @PostMapping
    public ServiceResponse saveUserGroup(@Valid @RequestBody UserGroupDto userGroupDto) throws GenericException{
        return new ServiceResponse(null, userGroupService.save(userGroupDto));
    }

    @GetMapping("/{id}")
    public ServiceResponse getUserGroupById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, userGroupService.getById(id));
    }

    @GetMapping
    public ServiceResponse getUserGroupBySearchCriteria(UserGroupSearchCriteria criteria, @PageableDefault(value = 10)Pageable pageable) throws GenericException{
        return new ServiceResponse(null, userGroupService.getBySearchCriteria(criteria, pageable));
    }
    @PutMapping("/{id}")
    public ServiceResponse updateUserGroupById(@PathVariable("id") Long id, @Valid @RequestBody UserGroupDto countryDto) throws GenericException{
        return new ServiceResponse(null, userGroupService.updateById(id, countryDto));
    }
    @DeleteMapping("/{id}")
    public ServiceResponse deleteUserGroupById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, userGroupService.deleteById(id));
    }

}
