package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.AuthorityModel;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.Authority;
import com.example.learnTogether.repository.AuthorityRepository;
import com.example.learnTogether.services.AuthorityService;
import com.example.learnTogether.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);


    @Autowired
    AuthorityRepository authorityRepository;

    public ServiceResponse<AuthorityModel> addNewAuthority(AuthorityModel authorityModel) throws GenericException {
        try {
            Authority authority = new Authority();
            Utils.copyProperty(authorityModel, authority);
            authority = authorityRepository.save(authority);
            Utils.copyProperty(authority, authorityModel);
            return new ServiceResponse(Utils.getSuccessResponse(), authorityModel);
        }catch (Exception e){
            logger.error("Exception occurred while adding new authority!");
            throw new GenericException("Exception occurred while adding new authority!", e);
        }
    }
}
