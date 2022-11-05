package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.TestHistoryDto;
import com.example.learnTogether.dto.request.TestHistorySearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.TestHistory;
import com.example.learnTogether.repository.TestHistoryRepository;
import com.example.learnTogether.services.TestHistoryService;
import com.example.learnTogether.specifications.TestHistorySearchSpecifications;
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
public class TestHistoryServiceImpls implements TestHistoryService {
    Logger logger = LoggerFactory.getLogger(TestHistoryServiceImpls.class);

    private final TestHistoryRepository testHistoryRepository;

    @Autowired
    TestHistoryServiceImpls(TestHistoryRepository testHistoryRepository){
        this.testHistoryRepository = testHistoryRepository;
    }

    @Override
    public TestHistory save(TestHistoryDto testHistoryDto) throws GenericException {
        try {
            TestHistory testHistory = new TestHistory();
            Utils.copyProperty(testHistoryDto, testHistory);
            testHistory = testHistoryRepository.save(testHistory);
            return testHistory;
        }catch (Exception e){
            logger.error("Exception occurred while saving, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public TestHistory getById(Long id) throws GenericException {
        try {
            Optional<TestHistory> optionalTestHistory = testHistoryRepository.findById(id);
            return optionalTestHistory.isPresent() ? optionalTestHistory.get(): null;
        }catch (Exception e){
            logger.error("Exception occurred while getting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Page<TestHistory> getBySearchCriteria(TestHistorySearchCriteria criteria, Pageable pageable) throws GenericException {
        try{
            return testHistoryRepository.findAll(TestHistorySearchSpecifications.withId(criteria.getId())
                            .and(TestHistorySearchSpecifications.withName(criteria.getName()))
                            .and(TestHistorySearchSpecifications.withCode(criteria.getCode()))
                    ,pageable);

        }catch (Exception e){
            logger.error("Exception occurred while getting by search criteria, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public TestHistory updateById(Long id, TestHistoryDto commentDto) throws GenericException {
        try {
            Optional<TestHistory> optionalTestHistory = testHistoryRepository.findById(id);
            if (!optionalTestHistory.isPresent()) throw new GenericException(Defs.NOT_FOUND);

            TestHistory comment = new TestHistory();
            Utils.copyProperty(commentDto, comment);
            comment = testHistoryRepository.save(comment);
            return comment;
        }catch (Exception e){
            logger.error("Exception occurred while updating by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteById(Long id) throws GenericException{
        try {
            Optional<TestHistory> optionalTestHistory = testHistoryRepository.findById(id);
            if(!optionalTestHistory.isPresent()) throw new GenericException(Defs.NOT_FOUND);
            TestHistory testHistory = new TestHistory();
            testHistory.setStatus(false);
            testHistoryRepository.save(testHistory);
            return true;
        }catch (Exception e){
            logger.error("Exception occurred while deleting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }
}
