package com.example.learnTogether.services;

import com.example.learnTogether.dto.TestHistoryDto;
import com.example.learnTogether.dto.request.TestHistorySearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.TestHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestHistoryService {
    TestHistory save(TestHistoryDto testHistoryDto) throws GenericException;
    TestHistory getById(Long id) throws GenericException;
    Page<TestHistory> getBySearchCriteria(TestHistorySearchCriteria criteria, Pageable pageable) throws GenericException;
    TestHistory updateById(Long id, TestHistoryDto testHistoryDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
}
