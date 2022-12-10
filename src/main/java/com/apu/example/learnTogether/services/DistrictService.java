package com.apu.example.learnTogether.services;

import com.apu.example.learnTogether.dto.DistrictDto;
import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.request.DistrictSearchCriteria;
import com.apu.example.learnTogether.models.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DistrictService {
    District save(DistrictDto districtDto) throws GenericException;
    District getById(Long id) throws GenericException;
    Page<District> getBySearchCriteria(DistrictSearchCriteria criteria, Pageable pageable) throws GenericException;
    District updateById(Long id, DistrictDto districtDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
}
