package com.apu.example.learnTogether.controllers;

import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.CommentDto;
import com.apu.example.learnTogether.dto.request.CommentSearchCriteria;
import com.apu.example.learnTogether.dto.response.ServiceResponse;
import com.apu.example.learnTogether.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ServiceResponse saveComment(@Valid @RequestBody CommentDto commentDto) throws GenericException {
        return new ServiceResponse(null, commentService.save(commentDto));
    }

    @GetMapping("/{id}")
    public ServiceResponse getCommentById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, commentService.getById(id));
    }

    @GetMapping
    public ServiceResponse getCommentBySearchCriteria(CommentSearchCriteria criteria, @PageableDefault(value = 10)Pageable pageable) throws GenericException{
        return new ServiceResponse(null, commentService.getBySearchCriteria(criteria, pageable));
    }
    @PutMapping("/{id}")
    public ServiceResponse updateCommentById(@PathVariable("id") Long id, @Valid @RequestBody CommentDto countryDto) throws GenericException{
        return new ServiceResponse(null, commentService.updateById(id, countryDto));
    }
    @DeleteMapping("/{id}")
    public ServiceResponse deleteCommentById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, commentService.deleteById(id));
    }

}
