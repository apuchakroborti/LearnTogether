package com.example.learnTogether.services;

import com.example.learnTogether.dto.AuthorityModel;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;


public interface AuthorityService {
    ServiceResponse<AuthorityModel> addNewAuthority(AuthorityModel authorityModel) throws GenericException;
}
