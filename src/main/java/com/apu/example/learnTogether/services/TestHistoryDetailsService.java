package com.apu.example.learnTogether.services;

import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.models.TestHistoryDetails;
import com.apu.example.learnTogether.dto.TestHistoryDetailsDto;
import com.apu.example.learnTogether.dto.request.TestHistoryDetailsSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestHistoryDetailsService {
    TestHistoryDetails save(TestHistoryDetailsDto testHistoryDetailsDto) throws GenericException;
    TestHistoryDetails getById(Long id) throws GenericException;
    Page<TestHistoryDetails> getBySearchCriteria(TestHistoryDetailsSearchCriteria criteria, Pageable pageable) throws GenericException;
    TestHistoryDetails updateById(Long id, TestHistoryDetailsDto testHistoryDetailsDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
}
