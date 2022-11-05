package com.example.learnTogether.services;

import com.example.learnTogether.dto.CommentDto;
import com.example.learnTogether.dto.request.CommentSearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Comment save(CommentDto commentDto) throws GenericException;
    Comment getById(Long id) throws GenericException;
    Page<Comment> getBySearchCriteria(CommentSearchCriteria criteria, Pageable pageable) throws GenericException;
    Comment updateById(Long id, CommentDto commentDto) throws GenericException;
    Boolean deleteById(Long id) throws GenericException;
}
