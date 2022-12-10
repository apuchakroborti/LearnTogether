package com.apu.example.learnTogether.services;

import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.OptionDto;
import com.apu.example.learnTogether.dto.request.OptionSearchCriteria;
import com.apu.example.learnTogether.models.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OptionService {
    Option save(OptionDto optionDto) throws GenericException;
    Option getById(Long id) throws GenericException;
    Page<Option> getBySearchCriteria(OptionSearchCriteria criteria, Pageable pageable) throws GenericException;
    Option updateById(Long id, OptionDto optionDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
    Option getOrCreate(OptionDto optionDto) throws GenericException;
}
