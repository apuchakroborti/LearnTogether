package com.apu.example.learnTogether.controllers;

import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.dto.ProfessionDto;
import com.apu.example.learnTogether.dto.request.ProfessionSearchCriteria;
import com.apu.example.learnTogether.dto.response.ServiceResponse;
import com.apu.example.learnTogether.services.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/save-from-file")
    public ServiceResponse saveProfessionFromFile(@RequestBody String filePath ) throws GenericException {
        return new ServiceResponse(null, professionService.saveFromFile(filePath));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
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
