package com.apu.example.learnTogether.services;

import com.apu.example.learnTogether.dto.request.CountrySearchCriteria;
import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.CountryDto;
import com.apu.example.learnTogether.models.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CountryService {
    Country save(CountryDto countryDto) throws GenericException;
    Country getById(Long id) throws GenericException;
    Page<Country> getBySearchCriteria(CountrySearchCriteria criteria, Pageable pageable) throws GenericException;
    Country updateById(Long id, CountryDto countryDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
}
