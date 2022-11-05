package com.example.learnTogether.services;

import com.example.learnTogether.dto.TestHistoryDetailsDto;
import com.example.learnTogether.dto.TestHistoryDto;
import com.example.learnTogether.dto.request.TestHistoryDetailsSearchCriteria;
import com.example.learnTogether.dto.request.TestHistorySearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.TestHistory;
import com.example.learnTogether.models.TestHistoryDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestHistoryDetailsService {
    TestHistoryDetails save(TestHistoryDetailsDto testHistoryDetailsDto) throws GenericException;
    TestHistoryDetails getById(Long id) throws GenericException;
    Page<TestHistoryDetails> getBySearchCriteria(TestHistoryDetailsSearchCriteria criteria, Pageable pageable) throws GenericException;
    TestHistoryDetails updateById(Long id, TestHistoryDetailsDto testHistoryDetailsDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
}
