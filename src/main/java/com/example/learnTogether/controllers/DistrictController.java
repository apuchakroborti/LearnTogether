package com.example.learnTogether.controllers;

import com.example.learnTogether.dto.DistrictDto;
import com.example.learnTogether.dto.request.DistrictSearchCriteria;
import com.example.learnTogether.dto.response.ServiceResponse;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.District;
import com.example.learnTogether.services.DistrictService;
import com.example.learnTogether.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;


@RestController
@RequestMapping("/api/district")
public class DistrictController {
    private final DistrictService districtService;

    @Autowired
    DistrictController(DistrictService districtService){
        this.districtService = districtService;
    }

    @PostMapping
    public ServiceResponse saveDistrict(@Valid @RequestBody DistrictDto districtDto) throws GenericException{
        District district = districtService.save(districtDto);
        if(Objects.isNull(district)){
            return new ServiceResponse(Utils.getNotFoundError(), null);
        }
        Utils.copyProperty(district, districtDto);
        return new ServiceResponse(Utils.getSuccessResponse(), districtDto);
    }

    @GetMapping("/{id}")
    public ServiceResponse getDistrictById(@PathVariable("id") Long id) throws GenericException{
        District district = districtService.getById(id);
        if(Objects.isNull(district)){
            return new ServiceResponse(Utils.getNotFoundError(), null);
        }

        DistrictDto districtDto = new DistrictDto();

        Utils.copyProperty(district, districtDto);

        return new ServiceResponse(Utils.getSuccessResponse(), districtDto);
    }

    @GetMapping
    public ServiceResponse getDistrictBySearchCriteria(DistrictSearchCriteria criteria, @PageableDefault(value = 10)Pageable pageable) throws GenericException{

        return new ServiceResponse(Utils.getSuccessResponse(), Utils.toDtoList(districtService.getBySearchCriteria(criteria, pageable), DistrictDto.class));
    }
    @PutMapping("/{id}")
    public ServiceResponse updateDistrictById(@PathVariable("id") Long id, @Valid @RequestBody DistrictDto districtDto) throws GenericException{
        District district = districtService.save(districtDto);
        if(Objects.isNull(district)){
            return new ServiceResponse(Utils.getNotFoundError(), null);
        }
        Utils.copyProperty(district, districtDto);
        return new ServiceResponse(Utils.getSuccessResponse(), districtDto);
    }
    @DeleteMapping("/{id}")
    public ServiceResponse deleteDistrictById(@PathVariable("id") Long id) throws GenericException{
        return new ServiceResponse(Utils.getSuccessResponse(), districtService.deleteById(id));
    }
}
