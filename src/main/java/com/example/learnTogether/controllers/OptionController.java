package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.OptionDto;
import com.example.learnTogether.dto.request.OptionSearchCriteria;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/option")
public class OptionController {
    private final OptionService optionService;

    @Autowired
    OptionController(OptionService optionService){
        this.optionService = optionService;
    }

    @PostMapping
    public ServiceResponse saveOption(@Valid @RequestBody OptionDto optionDto) throws GenericException{
        return new ServiceResponse(null, optionService.save(optionDto));
    }

    @GetMapping("/{id}")
    public ServiceResponse getOptionById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, optionService.getById(id));
    }

    @GetMapping
    public ServiceResponse getOptionBySearchCriteria(OptionSearchCriteria criteria, @PageableDefault(value = 10)Pageable pageable) throws GenericException{
        return new ServiceResponse(null, optionService.getBySearchCriteria(criteria, pageable));
    }
    @PutMapping("/{id}")
    public ServiceResponse updateOptionById(@PathVariable("id") Long id, @Valid @RequestBody OptionDto countryDto) throws GenericException{
        return new ServiceResponse(null, optionService.updateById(id, countryDto));
    }
    @DeleteMapping("/{id}")
    public ServiceResponse deleteOptionById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, optionService.deleteById(id));
    }
}
