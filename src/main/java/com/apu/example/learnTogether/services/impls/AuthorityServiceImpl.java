package com.apu.example.learnTogether.services.impls;

import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.repository.AuthorityRepository;
import com.apu.example.learnTogether.services.AuthorityService;
import com.apu.example.learnTogether.dto.AuthorityModel;
import com.apu.example.learnTogether.dto.response.ServiceResponse;
import com.apu.example.learnTogether.models.Authority;
import com.apu.example.learnTogether.utils.Utils;
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
