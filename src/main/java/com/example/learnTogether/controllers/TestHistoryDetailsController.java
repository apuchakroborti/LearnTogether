package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.TestHistoryDetailsDto;
import com.example.learnTogether.dto.request.TestHistoryDetailsSearchCriteria;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.services.TestHistoryDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/test-history-details")
public class TestHistoryDetailsController {
    private final TestHistoryDetailsService testHistoryDetailsService;

    @Autowired
    TestHistoryDetailsController(TestHistoryDetailsService testHistoryDetailsService){
        this.testHistoryDetailsService = testHistoryDetailsService;
    }

    @PostMapping
    public ServiceResponse saveTestHistoryDetails(@Valid @RequestBody TestHistoryDetailsDto testHistoryDetailsDto) throws GenericException{
        return new ServiceResponse(null, testHistoryDetailsService.save(testHistoryDetailsDto));
    }

    @GetMapping("/{id}")
    public ServiceResponse getTestHistoryDetailsById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, testHistoryDetailsService.getById(id));
    }

    @GetMapping
    public ServiceResponse getTestHistoryDetailsBySearchCriteria(TestHistoryDetailsSearchCriteria criteria, @PageableDefault(value = 10)Pageable pageable) throws GenericException{
        return new ServiceResponse(null, testHistoryDetailsService.getBySearchCriteria(criteria, pageable));
    }
    @PutMapping("/{id}")
    public ServiceResponse updateTestHistoryDetailsById(@PathVariable("id") Long id, @Valid @RequestBody TestHistoryDetailsDto countryDto) throws GenericException{
        return new ServiceResponse(null, testHistoryDetailsService.updateById(id, countryDto));
    }
    @DeleteMapping("/{id}")
    public ServiceResponse deleteTestHistoryDetailsById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, testHistoryDetailsService.deleteById(id));
    }

}
