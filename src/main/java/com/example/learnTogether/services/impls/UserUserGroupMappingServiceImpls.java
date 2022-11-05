package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.GroupMappingDto;
import com.example.learnTogether.dto.request.GroupMappingSearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.UserGroupMapping;
import com.example.learnTogether.repository.GroupMappingRepository;
import com.example.learnTogether.services.UserGroupMappingService;
import com.example.learnTogether.specifications.GroupMappingSearchSpecifications;
import com.example.learnTogether.utils.Defs;
import com.example.learnTogether.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUserGroupMappingServiceImpls implements UserGroupMappingService {
    Logger logger = LoggerFactory.getLogger(UserUserGroupMappingServiceImpls.class);

    private final GroupMappingRepository groupMappingRepository;

    @Autowired
    UserUserGroupMappingServiceImpls(GroupMappingRepository groupMappingRepository){
        this.groupMappingRepository = groupMappingRepository;
    }

    @Override
    public UserGroupMapping save(GroupMappingDto groupMappingDto) throws GenericException {
        try {
            UserGroupMapping userGroupMapping = new UserGroupMapping();
            Utils.copyProperty(groupMappingDto, userGroupMapping);
            userGroupMapping = groupMappingRepository.save(userGroupMapping);
            return userGroupMapping;
        }catch (Exception e){
            logger.error("Exception occurred while saving, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public UserGroupMapping getById(Long id) throws GenericException {
        try {
            Optional<UserGroupMapping> optionalUserGroupMapping = groupMappingRepository.findById(id);
            return optionalUserGroupMapping.isPresent() ? optionalUserGroupMapping.get(): null;
        }catch (Exception e){
            logger.error("Exception occurred while getting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Page<UserGroupMapping> getBySearchCriteria(GroupMappingSearchCriteria criteria, Pageable pageable) throws GenericException {
        try{
            return groupMappingRepository.findAll(GroupMappingSearchSpecifications.withId(criteria.getId())
                            .and(GroupMappingSearchSpecifications.withUserId(criteria.getUserProfileDto().getId()))
                            .and(GroupMappingSearchSpecifications.withGroupId(criteria.getUserGroupDto().getId()))
                    ,pageable);

        }catch (Exception e){
            logger.error("Exception occurred while getting by search criteria, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public UserGroupMapping updateById(Long id, GroupMappingDto commentDto) throws GenericException {
        try {
            Optional<UserGroupMapping> optionalUserGroupMapping = groupMappingRepository.findById(id);
            if (!optionalUserGroupMapping.isPresent()) throw new GenericException(Defs.NOT_FOUND);

            UserGroupMapping userGroupMapping = new UserGroupMapping();
            Utils.copyProperty(commentDto, userGroupMapping);
            userGroupMapping = groupMappingRepository.save(userGroupMapping);
            return userGroupMapping;
        }catch (Exception e){
            logger.error("Exception occurred while updating by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteById(Long id) throws GenericException{
        try {
            Optional<UserGroupMapping> optionalUserGroupMapping = groupMappingRepository.findById(id);
            if(!optionalUserGroupMapping.isPresent()) throw new GenericException(Defs.NOT_FOUND);

            UserGroupMapping userGroupMapping = optionalUserGroupMapping.get();
            groupMappingRepository.delete(userGroupMapping);
            return true;
        }catch (Exception e){
            logger.error("Exception occurred while deleting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }
}
