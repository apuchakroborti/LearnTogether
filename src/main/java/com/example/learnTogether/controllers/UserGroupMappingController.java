package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.GroupMappingDto;
import com.example.learnTogether.dto.request.GroupMappingSearchCriteria;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.services.UserGroupMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/user-group-mapping")
public class UserGroupMappingController {
    private final UserGroupMappingService userGroupMappingService;

    @Autowired
    UserGroupMappingController(UserGroupMappingService countryService){
        this.userGroupMappingService = countryService;
    }

    @PostMapping
    public ServiceResponse saveUserGroupMapping(@Valid @RequestBody GroupMappingDto groupMappingDto) throws GenericException{
        return new ServiceResponse(null, userGroupMappingService.save(groupMappingDto));
    }

    @GetMapping("/{id}")
    public ServiceResponse getUserGroupMappingById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, userGroupMappingService.getById(id));
    }

    @GetMapping
    public ServiceResponse getUserGroupMappingBySearchCriteria(GroupMappingSearchCriteria criteria, @PageableDefault(value = 10)Pageable pageable) throws GenericException{
        return new ServiceResponse(null, userGroupMappingService.getBySearchCriteria(criteria, pageable));
    }
    @PutMapping("/{id}")
    public ServiceResponse updateUserGroupMappingById(@PathVariable("id") Long id, @Valid @RequestBody GroupMappingDto groupMappingDto) throws GenericException{
        return new ServiceResponse(null, userGroupMappingService.updateById(id, groupMappingDto));
    }
    @DeleteMapping("/{id}")
    public ServiceResponse deleteCountryById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, userGroupMappingService.deleteById(id));
    }

}
