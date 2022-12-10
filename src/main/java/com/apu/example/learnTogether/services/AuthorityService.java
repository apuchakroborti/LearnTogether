package com.apu.example.learnTogether.services;

import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.AuthorityModel;
import com.apu.example.learnTogether.dto.response.ServiceResponse;


public interface AuthorityService {
    ServiceResponse<AuthorityModel> addNewAuthority(AuthorityModel authorityModel) throws GenericException;
}
