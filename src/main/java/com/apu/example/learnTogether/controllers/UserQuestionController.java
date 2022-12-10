package com.apu.example.learnTogether.controllers;

import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.UserQuestionDto;
import com.apu.example.learnTogether.dto.request.UserQuestionSearchCriteria;
import com.apu.example.learnTogether.dto.response.ServiceResponse;
import com.apu.example.learnTogether.services.UserQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/user-question")
public class UserQuestionController {
    private final UserQuestionService userQuestionService;

    @Autowired
    UserQuestionController(UserQuestionService userQuestionService){
        this.userQuestionService = userQuestionService;
    }

    @PostMapping
    public ServiceResponse saveUserQuestion(@Valid @RequestBody UserQuestionDto userQuestionDto) throws GenericException {
        return new ServiceResponse(null, userQuestionService.save(userQuestionDto));
    }

    @GetMapping("/{id}")
    public ServiceResponse getUserQuestionById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, userQuestionService.getById(id));
    }

    @GetMapping
    public ServiceResponse getUserQuestionBySearchCriteria(UserQuestionSearchCriteria criteria, @PageableDefault(value = 10)Pageable pageable) throws GenericException{
        return new ServiceResponse(null, userQuestionService.getBySearchCriteria(criteria, pageable));
    }
    @PutMapping("/{id}")
    public ServiceResponse updateUserQuestionById(@PathVariable("id") Long id, @Valid @RequestBody UserQuestionDto countryDto) throws GenericException{
        return new ServiceResponse(null, userQuestionService.updateById(id, countryDto));
    }
    @DeleteMapping("/{id}")
    public ServiceResponse deleteUserQuestionById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, userQuestionService.deleteById(id));
    }

}
