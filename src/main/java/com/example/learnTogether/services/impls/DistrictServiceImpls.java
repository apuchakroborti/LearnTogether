package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.DistrictDto;
import com.example.learnTogether.dto.request.DistrictSearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.Country;
import com.example.learnTogether.models.District;
import com.example.learnTogether.models.Option;
import com.example.learnTogether.repository.CountryRepository;
import com.example.learnTogether.repository.DistrictRepository;
import com.example.learnTogether.services.DistrictService;
import com.example.learnTogether.specifications.DistrictSearchSpecifications;
import com.example.learnTogether.utils.Defs;
import com.example.learnTogether.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistrictServiceImpls implements DistrictService {
    Logger logger = LoggerFactory.getLogger(DistrictServiceImpls.class);

    private final DistrictRepository districtRepository;
    private final CountryRepository countryRepository;

    @Autowired
    DistrictServiceImpls(DistrictRepository districtRepository,
                         CountryRepository countryRepository){
        this.districtRepository = districtRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public District save(DistrictDto districtDto) throws GenericException {
        try {
            Optional<Country> countryOptional = countryRepository.findById(districtDto.getId());
            if(!countryOptional.isPresent())throw new GenericException("Country "+Defs.NOT_FOUND);

            District district = new District();
            district.setCountry(countryOptional.get());

            Utils.copyProperty(districtDto, district);
            district = districtRepository.save(district);
            return district;
        }catch (Exception e){
            logger.error("Exception occurred while saving, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public District getById(Long id) throws GenericException {
        try {
            Optional<District> optionalDistrict = districtRepository.findById(id);
            return optionalDistrict.isPresent() ? optionalDistrict.get(): null;
        }catch (Exception e){
            logger.error("Exception occurred while getting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Page<District> getBySearchCriteria(DistrictSearchCriteria criteria, Pageable pageable) throws GenericException {
        try{
            return districtRepository.findAll(DistrictSearchSpecifications.withId(criteria.getId())
                            .and(DistrictSearchSpecifications.withName(criteria.getName()))
                            .and(DistrictSearchSpecifications.withCode(criteria.getCode()))
                    ,pageable);

        }catch (Exception e){
            logger.error("Exception occurred while getting by search criteria, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public District updateById(Long id, DistrictDto commentDto) throws GenericException {
        try {
            Optional<District> optionalDistrict = districtRepository.findById(id);
            if (!optionalDistrict.isPresent()) throw new GenericException(Defs.NOT_FOUND);

            District comment = new District();
            Utils.copyProperty(commentDto, comment);
            comment = districtRepository.save(comment);
            return comment;
        }catch (Exception e){
            logger.error("Exception occurred while updating by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteById(Long id) throws GenericException{
        try {
            Optional<District> optionalDistrict = districtRepository.findById(id);
            if(!optionalDistrict.isPresent()) throw new GenericException(Defs.NOT_FOUND);
            District district = new District();
            districtRepository.save(district);
            return true;
        }catch (Exception e){
            logger.error("Exception occurred while deleting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }
}
