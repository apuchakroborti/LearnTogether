package com.example.learnTogether.services;

import com.example.learnTogether.dto.ProfessionDto;
import com.example.learnTogether.dto.request.ProfessionSearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.Profession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfessionService {
    Boolean saveFromFile(String filePath) throws GenericException;
    Profession save(ProfessionDto professionDto) throws GenericException;
    Profession getById(Long id) throws GenericException;
    Page<Profession> getBySearchCriteria(ProfessionSearchCriteria criteria, Pageable pageable) throws GenericException;
    Profession updateById(Long id, ProfessionDto professionDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
}
