package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.CountryDto;
import com.example.learnTogether.dto.request.CountrySearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.Country;
import com.example.learnTogether.repository.CountryRepository;
import com.example.learnTogether.services.CountryService;
import com.example.learnTogether.specifications.CountrySearchSpecifications;
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
public class CountryServiceImpls implements CountryService {
    Logger logger = LoggerFactory.getLogger(CountryServiceImpls.class);

    private final CountryRepository countryRepository;

    @Autowired
    CountryServiceImpls(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @Override
    public Country save(CountryDto commentDto) throws GenericException {
        try {
            Country country = new Country();
            Utils.copyProperty(commentDto, country);
            country = countryRepository.save(country);
            return country;
        }catch (Exception e){
            logger.error("Exception occurred while saving, message: {}", e.getMessage());
            throw new GenericException(e.getMessage(), e);
        }
    }

    @Override
    public Country getById(Long id) throws GenericException {
        try {
            Optional<Country> optionalCountry = countryRepository.findById(id);
            return optionalCountry.isPresent() ? optionalCountry.get(): null;
        }catch (Exception e){
            logger.error("Exception occurred while getting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Page<Country> getBySearchCriteria(CountrySearchCriteria criteria, Pageable pageable) throws GenericException {
        try{
            return countryRepository.findAll(CountrySearchSpecifications.withId(criteria.getId())
                            .and(CountrySearchSpecifications.withName(criteria.getName()))
                            .and(CountrySearchSpecifications.withCode(criteria.getCode()))
                    ,pageable);

        }catch (Exception e){
            logger.error("Exception occurred while getting by search criteria, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Country updateById(Long id, CountryDto commentDto) throws GenericException {
        try {
            Optional<Country> optionalCountry = countryRepository.findById(id);
            if (!optionalCountry.isPresent()) throw new GenericException(Defs.NOT_FOUND);

            Country comment = new Country();
            Utils.copyProperty(commentDto, comment);
            comment = countryRepository.save(comment);
            return comment;
        }catch (Exception e){
            logger.error("Exception occurred while updating by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteById(Long id) throws GenericException{
        try {
            Optional<Country> optionalCountry = countryRepository.findById(id);
            if(!optionalCountry.isPresent()) throw new GenericException(Defs.NOT_FOUND);
            Country country = new Country();
            countryRepository.deleteById(id);
            return true;
        }catch (Exception e){
            logger.error("Exception occurred while deleting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }
}
