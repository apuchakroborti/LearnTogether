package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.TestHistoryDto;
import com.example.learnTogether.dto.request.TestHistorySearchCriteria;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.services.TestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/test-history")
public class TestHistoryController {
    private final TestHistoryService testHistoryService;

    @Autowired
    TestHistoryController(TestHistoryService testHistoryService){
        this.testHistoryService = testHistoryService;
    }

    @PostMapping
    public ServiceResponse saveTestHistory(@Valid @RequestBody TestHistoryDto testHistoryDto) throws GenericException{
        return new ServiceResponse(null, testHistoryService.save(testHistoryDto));
    }

    @GetMapping("/{id}")
    public ServiceResponse getTestHistoryById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, testHistoryService.getById(id));
    }

    @GetMapping
    public ServiceResponse getTestHistoryBySearchCriteria(TestHistorySearchCriteria criteria, @PageableDefault(value = 10)Pageable pageable) throws GenericException{
        return new ServiceResponse(null, testHistoryService.getBySearchCriteria(criteria, pageable));
    }
    @PutMapping("/{id}")
    public ServiceResponse updateTestHistoryById(@PathVariable("id") Long id, @Valid @RequestBody TestHistoryDto countryDto) throws GenericException{
        return new ServiceResponse(null, testHistoryService.updateById(id, countryDto));
    }
    @DeleteMapping("/{id}")
    public ServiceResponse deleteTestHistoryById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, testHistoryService.deleteById(id));
    }

}
