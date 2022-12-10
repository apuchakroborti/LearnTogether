package com.apu.example.learnTogether.services;

import com.apu.example.learnTogether.dto.request.ProfessionSearchCriteria;
import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.ProfessionDto;
import com.apu.example.learnTogether.models.Profession;
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
