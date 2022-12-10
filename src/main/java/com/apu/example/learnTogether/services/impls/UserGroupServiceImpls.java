package com.apu.example.learnTogether.services.impls;

import com.apu.example.learnTogether.specifications.UserGroupSearchSpecifications;
import com.apu.example.learnTogether.dto.UserGroupDto;
import com.apu.example.learnTogether.dto.request.UserGroupSearchCriteria;
import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.models.UserGroup;
import com.apu.example.learnTogether.repository.UserGroupRepository;
import com.apu.example.learnTogether.services.UserGroupService;
import com.apu.example.learnTogether.utils.Defs;
import com.apu.example.learnTogether.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserGroupServiceImpls implements UserGroupService {
    Logger logger = LoggerFactory.getLogger(UserGroupServiceImpls.class);

    private final UserGroupRepository userGroupRepository;

    @Autowired
    UserGroupServiceImpls(UserGroupRepository userGroupRepository){
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public UserGroup save(UserGroupDto userGroupDto) throws GenericException {
        try {
            UserGroup userGroup = new UserGroup();
            Utils.copyProperty(userGroupDto, userGroup);
            userGroup = userGroupRepository.save(userGroup);
            return userGroup;
        }catch (Exception e){
            logger.error("Exception occurred while saving, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public UserGroup getById(Long id) throws GenericException {
        try {
            Optional<UserGroup> optionalUserGroup = userGroupRepository.findById(id);
            return optionalUserGroup.isPresent() ? optionalUserGroup.get(): null;
        }catch (Exception e){
            logger.error("Exception occurred while getting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Page<UserGroup> getBySearchCriteria(UserGroupSearchCriteria criteria, Pageable pageable) throws GenericException {
        try{
            return userGroupRepository.findAll(UserGroupSearchSpecifications.withId(criteria.getId())
                            .and(UserGroupSearchSpecifications.withGroupName(criteria.getGroupName()))
                            .and(UserGroupSearchSpecifications.withStatus(true))
                    ,pageable);

        }catch (Exception e){
            logger.error("Exception occurred while getting by search criteria, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public UserGroup updateById(Long id, UserGroupDto userGroupDto) throws GenericException {
        try {
            Optional<UserGroup> optionalUserGroup = userGroupRepository.findById(id);
            if (!optionalUserGroup.isPresent()) throw new GenericException(Defs.NOT_FOUND);

            UserGroup userGroup = optionalUserGroup.get();
            Utils.copyProperty(userGroupDto, userGroup);
            userGroup = userGroupRepository.save(userGroup);
            return userGroup;
        }catch (Exception e){
            logger.error("Exception occurred while updating by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteById(Long id) throws GenericException{
        try {
            Optional<UserGroup> optionalUserGroup = userGroupRepository.findById(id);
            if(!optionalUserGroup.isPresent()) throw new GenericException(Defs.NOT_FOUND);
            UserGroup userGroup = optionalUserGroup.get();
            userGroup.setStatus(false);
            userGroupRepository.save(userGroup);
            return true;
        }catch (Exception e){
            logger.error("Exception occurred while deleting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }
}
