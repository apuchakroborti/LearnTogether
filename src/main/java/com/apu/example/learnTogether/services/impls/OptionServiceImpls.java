package com.apu.example.learnTogether.services.impls;

import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.services.OptionService;
import com.apu.example.learnTogether.specifications.OptionSearchSpecifications;
import com.apu.example.learnTogether.utils.Utils;
import com.apu.example.learnTogether.dto.OptionDto;
import com.apu.example.learnTogether.dto.request.OptionSearchCriteria;
import com.apu.example.learnTogether.models.Option;
import com.apu.example.learnTogether.repository.OptionRepository;
import com.apu.example.learnTogether.utils.Defs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class OptionServiceImpls implements OptionService {
    Logger logger = LoggerFactory.getLogger(OptionServiceImpls.class);

    private final OptionRepository optionRepository;

    @Autowired
    OptionServiceImpls(OptionRepository optionRepository){
        this.optionRepository = optionRepository;
    }

    @Override
    public Option save(OptionDto optionDto) throws GenericException {
        try {
            Option option = new Option();
            Utils.copyProperty(optionDto, option);
            option = optionRepository.save(option);
            return option;
        }catch (Exception e){
            logger.error("Exception occurred while saving, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Option getById(Long id) throws GenericException {
        try {
            Optional<Option> optionalOption = optionRepository.findById(id);
            return optionalOption.isPresent() ? optionalOption.get(): null;
        }catch (Exception e){
            logger.error("Exception occurred while getting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Page<Option> getBySearchCriteria(OptionSearchCriteria criteria, Pageable pageable) throws GenericException {
        try{
            return optionRepository.findAll(OptionSearchSpecifications.withId(criteria.getId())
                            .and(OptionSearchSpecifications.withValue(criteria.getValue()))
                    ,pageable);

        }catch (Exception e){
            logger.error("Exception occurred while getting by search criteria, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Option updateById(Long id, OptionDto commentDto) throws GenericException {
        try {
            Optional<Option> optionalOption = optionRepository.findById(id);
            if (!optionalOption.isPresent()) throw new GenericException(Defs.NOT_FOUND);

            Option option = new Option();
            Utils.copyProperty(commentDto, option);
            option = optionRepository.save(option);
            return option;
        }catch (Exception e){
            logger.error("Exception occurred while updating by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteById(Long id) throws GenericException{
        try {
            Optional<Option> optionalOption = optionRepository.findById(id);
            if(!optionalOption.isPresent()) throw new GenericException(Defs.NOT_FOUND);
            optionRepository.deleteById(id);
            return true;
        }catch (Exception e){
            logger.error("Exception occurred while deleting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Option getOrCreate(OptionDto optionDto) throws GenericException{
        try {
            Option option = this.getById(optionDto.getId());
            if(Objects.isNull(option)){
                option = this.save(optionDto);
            }
            return option;
        }catch (Exception e){
            logger.error("Error occurred while get or create option!");
            throw new GenericException(e.getMessage());
        }
    }
}
