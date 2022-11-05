package com.example.learnTogether.services;

import com.example.learnTogether.dto.GroupMappingDto;
import com.example.learnTogether.dto.request.GroupMappingSearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.UserGroupMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserGroupMappingService {
    UserGroupMapping save(GroupMappingDto countryDto) throws GenericException;
    UserGroupMapping getById(Long id) throws GenericException;
    Page<UserGroupMapping> getBySearchCriteria(GroupMappingSearchCriteria criteria, Pageable pageable) throws GenericException;
    UserGroupMapping updateById(Long id, GroupMappingDto groupMappingDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
}
