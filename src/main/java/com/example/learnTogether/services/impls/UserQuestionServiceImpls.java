package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.UserQuestionDto;
import com.example.learnTogether.dto.request.UserQuestionSearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.UserQuestion;
import com.example.learnTogether.repository.UserQuestionRepository;
import com.example.learnTogether.services.UserQuestionService;
import com.example.learnTogether.specifications.UserQuestionSearchSpecifications;
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
public class UserQuestionServiceImpls implements UserQuestionService {

    Logger logger = LoggerFactory.getLogger(UserQuestionServiceImpls.class);

    private final UserQuestionRepository userQuestionRepository;

    @Autowired
    UserQuestionServiceImpls(UserQuestionRepository userQuestionRepository){
        this.userQuestionRepository = userQuestionRepository;
    }

    @Override
    public UserQuestion save(UserQuestionDto commentDto) throws GenericException {
        try {
            UserQuestion userQuestion = new UserQuestion();
            Utils.copyProperty(commentDto, userQuestion);
            userQuestion = userQuestionRepository.save(userQuestion);
            return userQuestion;
        }catch (Exception e){
            logger.error("Exception occurred while saving, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public UserQuestion getById(Long id) throws GenericException {
        try {
            Optional<UserQuestion> optionalUserQuestion = userQuestionRepository.findById(id);
            return optionalUserQuestion.isPresent() ? optionalUserQuestion.get(): null;
        }catch (Exception e){
            logger.error("Exception occurred while getting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Page<UserQuestion> getBySearchCriteria(UserQuestionSearchCriteria criteria, Pageable pageable) throws GenericException {
        try{
            return userQuestionRepository.findAll(UserQuestionSearchSpecifications.withId(criteria.getId())
                            .and(UserQuestionSearchSpecifications.withUserId(criteria.getUserProfileDto().getId()))
                            .and(UserQuestionSearchSpecifications.withDescription(criteria.getDescription()))
                            .and(UserQuestionSearchSpecifications.withStatus(true))
                    ,pageable);

        }catch (Exception e){
            logger.error("Exception occurred while getting by search criteria, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public UserQuestion updateById(Long id, UserQuestionDto commentDto) throws GenericException {
        try {
            Optional<UserQuestion> optionalUserQuestion = userQuestionRepository.findById(id);
            if (!optionalUserQuestion.isPresent()) throw new GenericException(Defs.NOT_FOUND);

            UserQuestion userQuestion = optionalUserQuestion.get();
            Utils.copyProperty(commentDto, userQuestion);
            userQuestion = userQuestionRepository.save(userQuestion);
            return userQuestion;
        }catch (Exception e){
            logger.error("Exception occurred while updating by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteById(Long id) throws GenericException{
        try {
            Optional<UserQuestion> optionalUserQuestion = userQuestionRepository.findById(id);
            if(!optionalUserQuestion.isPresent()) throw new GenericException(Defs.NOT_FOUND);
            UserQuestion userQuestion = optionalUserQuestion.get();
            userQuestion.setStatus(false);
            userQuestionRepository.save(userQuestion);
            return true;
        }catch (Exception e){
            logger.error("Exception occurred while deleting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }
}
