package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.CountryDto;
import com.example.learnTogether.dto.request.CountrySearchCriteria;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.Country;
import com.example.learnTogether.services.CountryService;
import com.example.learnTogether.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;


@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @PostMapping
    public ServiceResponse saveCountry(@Valid @RequestBody CountryDto countryDto) throws GenericException{
        Country country = countryService.save(countryDto);
        if(Objects.isNull(country)){
            return new ServiceResponse(Utils.getInternalServerError(), null );
        }
        Utils.copyProperty(country, countryDto);
        return new ServiceResponse(Utils.getSuccessResponse(), countryDto);
    }

    @GetMapping("/{id}")
    public ServiceResponse getCountryById(@PathVariable("id") Long id) throws GenericException{
        Country country = countryService.getById(id);
        if(Objects.isNull(country)){
            return new ServiceResponse(Utils.getNotFoundError(), null );
        }
        CountryDto countryDto = new CountryDto();
        Utils.copyProperty(country, countryDto);

        return new ServiceResponse(Utils.getSuccessResponse(), countryDto);
    }

    @GetMapping
    public ServiceResponse getCountryBySearchCriteria(CountrySearchCriteria criteria, @PageableDefault(value = 10)Pageable pageable) throws GenericException{
        return new ServiceResponse(Utils.getSuccessResponse(), Utils.toDtoList(countryService.getBySearchCriteria(criteria, pageable), CountryDto.class));
    }
    @PutMapping("/{id}")
    public ServiceResponse updateCountryById(@PathVariable("id") Long id, @Valid @RequestBody CountryDto countryDto) throws GenericException{
        Country country = countryService.updateById(id, countryDto);
        if(Objects.isNull(country)){
            return new ServiceResponse(Utils.getNotFoundError(), null );
        }
        Utils.copyProperty(country, countryDto);

        return new ServiceResponse(Utils.getSuccessResponse(), countryService.updateById(id, countryDto));
    }
    @DeleteMapping("/{id}")
    public ServiceResponse deleteCountryById(@PathVariable("id") Long id) throws GenericException{

        return new ServiceResponse(Utils.getSuccessResponse(), countryService.deleteById(id));
    }

}
