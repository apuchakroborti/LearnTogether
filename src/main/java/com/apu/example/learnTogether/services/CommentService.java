package com.apu.example.learnTogether.services;

import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.CommentDto;
import com.apu.example.learnTogether.dto.request.CommentSearchCriteria;
import com.apu.example.learnTogether.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Comment save(CommentDto commentDto) throws GenericException;
    Comment getById(Long id) throws GenericException;
    Page<Comment> getBySearchCriteria(CommentSearchCriteria criteria, Pageable pageable) throws GenericException;
    Comment updateById(Long id, CommentDto commentDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
}
