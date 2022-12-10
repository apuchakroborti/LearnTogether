package com.apu.example.learnTogether.services;

import com.apu.example.learnTogether.dto.UserGroupDto;
import com.apu.example.learnTogether.dto.request.UserGroupSearchCriteria;
import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.models.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserGroupService {
    UserGroup save(UserGroupDto countryDto) throws GenericException;
    UserGroup getById(Long id) throws GenericException;
    Page<UserGroup> getBySearchCriteria(UserGroupSearchCriteria criteria, Pageable pageable) throws GenericException;
    UserGroup updateById(Long id, UserGroupDto userGroupDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
}
