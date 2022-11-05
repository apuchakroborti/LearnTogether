package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.ProfessionDto;
import com.example.learnTogether.dto.request.ProfessionSearchCriteria;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.services.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/profession")
public class ProfessionController {
    private final ProfessionService professionService;

    @Autowired
    ProfessionController(ProfessionService professionService){
        this.professionService = professionService;
    }

    @PostMapping
    public ServiceResponse saveProfession(@Valid @RequestBody ProfessionDto professionDto) throws GenericException{
        return new ServiceResponse(null, professionService.save(professionDto));
    }

    @GetMapping("/{id}")
    public ServiceResponse getProfessionById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, professionService.getById(id));
    }

    @GetMapping
    public ServiceResponse getProfessionBySearchCriteria(ProfessionSearchCriteria criteria, @PageableDefault(value = 10)Pageable pageable) throws GenericException{
        return new ServiceResponse(null, professionService.getBySearchCriteria(criteria, pageable));
    }
    @PutMapping("/{id}")
    public ServiceResponse updateProfessionById(@PathVariable("id") Long id, @Valid @RequestBody ProfessionDto countryDto) throws GenericException{
        return new ServiceResponse(null, professionService.updateById(id, countryDto));
    }
    @DeleteMapping("/{id}")
    public ServiceResponse deleteProfessionById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(null, professionService.deleteById(id));
    }

}
