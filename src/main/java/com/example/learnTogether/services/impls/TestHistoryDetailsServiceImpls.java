package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.TestHistoryDetailsDto;
import com.example.learnTogether.dto.request.TestHistoryDetailsSearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.TestHistoryDetails;
import com.example.learnTogether.repository.TestHistoryDetailsRepository;
import com.example.learnTogether.services.TestHistoryDetailsService;
import com.example.learnTogether.specifications.TestHistoryDetailsSearchSpecifications;
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
public class TestHistoryDetailsServiceImpls implements TestHistoryDetailsService {

    Logger logger = LoggerFactory.getLogger(TestHistoryDetailsServiceImpls.class);

    private final TestHistoryDetailsRepository testHistoryDetailsRepository;

    @Autowired
    TestHistoryDetailsServiceImpls(TestHistoryDetailsRepository testHistoryDetailsRepository){
        this.testHistoryDetailsRepository = testHistoryDetailsRepository;
    }

    @Override
    public TestHistoryDetails save(TestHistoryDetailsDto commentDto) throws GenericException {
        try {
            TestHistoryDetails testHistoryDetails = new TestHistoryDetails();
            Utils.copyProperty(commentDto, testHistoryDetails);
            testHistoryDetails = testHistoryDetailsRepository.save(testHistoryDetails);
            return testHistoryDetails;
        }catch (Exception e){
            logger.error("Exception occurred while saving, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public TestHistoryDetails getById(Long id) throws GenericException {
        try {
            Optional<TestHistoryDetails> optionalTestHistoryDetails = testHistoryDetailsRepository.findById(id);
            return optionalTestHistoryDetails.isPresent() ? optionalTestHistoryDetails.get(): null;
        }catch (Exception e){
            logger.error("Exception occurred while getting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Page<TestHistoryDetails> getBySearchCriteria(TestHistoryDetailsSearchCriteria criteria, Pageable pageable) throws GenericException {
        try{
            return testHistoryDetailsRepository.findAll(TestHistoryDetailsSearchSpecifications.withId(criteria.getId())
                            .and(TestHistoryDetailsSearchSpecifications.withTestHistoryId(criteria.getTestHistoryDto().getId()))
                            .and(TestHistoryDetailsSearchSpecifications.withQuestionId(criteria.getUserQuestionDto().getId()))
                    ,pageable);

        }catch (Exception e){
            logger.error("Exception occurred while getting by search criteria, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public TestHistoryDetails updateById(Long id, TestHistoryDetailsDto commentDto) throws GenericException {
        try {
            Optional<TestHistoryDetails> optionalTestHistoryDetails = testHistoryDetailsRepository.findById(id);
            if (!optionalTestHistoryDetails.isPresent()) throw new GenericException(Defs.NOT_FOUND);

            TestHistoryDetails comment = new TestHistoryDetails();
            Utils.copyProperty(commentDto, comment);
            comment = testHistoryDetailsRepository.save(comment);
            return comment;
        }catch (Exception e){
            logger.error("Exception occurred while updating by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteById(Long id) throws GenericException{
        try {
            Optional<TestHistoryDetails> optionalTestHistoryDetails = testHistoryDetailsRepository.findById(id);
            if(!optionalTestHistoryDetails.isPresent()) throw new GenericException(Defs.NOT_FOUND);
            TestHistoryDetails testHistoryDetails = optionalTestHistoryDetails.get();
            testHistoryDetailsRepository.delete(testHistoryDetails);
            return true;
        }catch (Exception e){
            logger.error("Exception occurred while deleting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }
}
