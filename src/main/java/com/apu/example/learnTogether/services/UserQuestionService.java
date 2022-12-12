package com.apu.example.learnTogether.services;

import com.apu.example.learnTogether.dto.request.UserQuestionSearchCriteria;
import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.UserQuestionDto;
import com.apu.example.learnTogether.models.UserQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQuestionService {
    UserQuestionDto save(UserQuestionDto userQuestionDto) throws GenericException;
    UserQuestion getById(Long id) throws GenericException;
    Page<UserQuestion> getBySearchCriteria(UserQuestionSearchCriteria criteria, Pageable pageable) throws GenericException;
    UserQuestion updateById(Long id, UserQuestionDto userQuestionDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
}
