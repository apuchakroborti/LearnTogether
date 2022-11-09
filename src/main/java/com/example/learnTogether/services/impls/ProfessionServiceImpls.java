package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.ProfessionDto;
import com.example.learnTogether.dto.request.ProfessionSearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.Profession;
import com.example.learnTogether.repository.ProfessionRepository;
import com.example.learnTogether.services.ProfessionService;
import com.example.learnTogether.specifications.ProfessionSearchSpecifications;
import com.example.learnTogether.utils.Defs;
import com.example.learnTogether.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessionServiceImpls implements ProfessionService {
    Logger logger = LoggerFactory.getLogger(ProfessionServiceImpls.class);

    private final ProfessionRepository professionRepository;

    @Autowired
    ProfessionServiceImpls(ProfessionRepository professionRepository){
        this.professionRepository = professionRepository;
    }

    @Override
    public Boolean saveFromFile(String filePath) throws GenericException{
        Optional<Profession> optionalProfession = professionRepository.findById(1L);
        if(optionalProfession.isPresent()){
            throw new GenericException("Already inserted profession data!");
        }
        List<Profession> professionList = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(filePath)))
        {

            reader.lines().forEach(
                    line->professionList.add(new Profession(line.toString()))
            );

            professionRepository.saveAll(professionList);
            return true;
        }
        catch (Exception e) {
            logger.error("Exception occurred while saving profession info from file, message: {}", e.getMessage());
            throw new GenericException(e.getMessage(), e);
        }

    };

    @Override
    public Profession save(ProfessionDto professionDto) throws GenericException {
        try {
            Profession profession = new Profession();
            Utils.copyProperty(professionDto, profession);

            profession = professionRepository.save(profession);
            return profession;
        }catch (Exception e){
            logger.error("Exception occurred while saving, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Profession getById(Long id) throws GenericException {
        try {
            Optional<Profession> optionalProfession = professionRepository.findById(id);
            return optionalProfession.isPresent() ? optionalProfession.get(): null;
        }catch (Exception e){
            logger.error("Exception occurred while getting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Page<Profession> getBySearchCriteria(ProfessionSearchCriteria criteria, Pageable pageable) throws GenericException {
        try{
            return professionRepository.findAll(ProfessionSearchSpecifications.withId(criteria.getId())
                            .and(ProfessionSearchSpecifications.withName(criteria.getName()))
                    ,pageable);

        }catch (Exception e){
            logger.error("Exception occurred while getting by search criteria, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Profession updateById(Long id, ProfessionDto commentDto) throws GenericException {
        try {
            Optional<Profession> optionalProfession = professionRepository.findById(id);
            if (!optionalProfession.isPresent()) throw new GenericException(Defs.NOT_FOUND);

            Profession comment = new Profession();
            Utils.copyProperty(commentDto, comment);
            comment = professionRepository.save(comment);
            return comment;
        }catch (Exception e){
            logger.error("Exception occurred while updating by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteById(Long id) throws GenericException{
        try {
            Optional<Profession> optionalProfession = professionRepository.findById(id);
            if(!optionalProfession.isPresent()) throw new GenericException(Defs.NOT_FOUND);
            professionRepository.deleteById(id);
            return true;
        }catch (Exception e){
            logger.error("Exception occurred while deleting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }


}
