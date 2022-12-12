package com.apu.example.learnTogether.services.impls;

import com.apu.example.learnTogether.dto.OptionDto;
import com.apu.example.learnTogether.exceptions.GenericException;
import com.apu.example.learnTogether.models.Option;
import com.apu.example.learnTogether.models.UserProfile;
import com.apu.example.learnTogether.repository.OptionRepository;
import com.apu.example.learnTogether.repository.UserProfileRepository;
import com.apu.example.learnTogether.services.OptionService;
import com.apu.example.learnTogether.specifications.UserQuestionSearchSpecifications;
import com.apu.example.learnTogether.utils.Utils;
import com.apu.example.learnTogether.dto.UserQuestionDto;
import com.apu.example.learnTogether.dto.request.UserQuestionSearchCriteria;
import com.apu.example.learnTogether.models.UserQuestion;
import com.apu.example.learnTogether.repository.UserQuestionRepository;
import com.apu.example.learnTogether.services.UserQuestionService;
import com.apu.example.learnTogether.utils.Defs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserQuestionServiceImpls implements UserQuestionService {

    Logger logger = LoggerFactory.getLogger(UserQuestionServiceImpls.class);

    private final UserQuestionRepository userQuestionRepository;
    private final UserProfileRepository userProfileRepository;
    private final OptionService optionService;

    @Autowired
    UserQuestionServiceImpls(UserQuestionRepository userQuestionRepository,
                             UserProfileRepository userProfileRepository,
                             OptionService optionService){
        this.userQuestionRepository = userQuestionRepository;
        this.userProfileRepository = userProfileRepository;
        this.optionService = optionService;
    }

    @Override
    public UserQuestionDto save(UserQuestionDto userQuestionDto) throws GenericException {
        try {
            UserQuestion userQuestion = new UserQuestion();
            Utils.copyProperty(userQuestionDto, userQuestion);

            Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userQuestionDto.getUserProfile().getId());
            if(!optionalUserProfile.isPresent()){
                throw new GenericException(Defs.USER_PROFILE_NOT_FOUND);
            }
            userQuestion.setUserProfile(optionalUserProfile.get());
            List<Option> optionList = new ArrayList<>();

            for(OptionDto optionDto: userQuestionDto.getOptionList()){
                Option  option = optionService.getOrCreate(optionDto);
                optionList.add(option);
            }
            userQuestion.setOptionList(optionList);
            Option answerOption = optionService.getOrCreate(userQuestionDto.getAnswer());
            userQuestion.setAnswer(answerOption);
            userQuestion.setElaboration(userQuestion.getDescription());
            userQuestion.setStatus(true);
            userQuestion.setCreateTime(LocalDateTime.now());

            userQuestion = userQuestionRepository.save(userQuestion);
            Utils.copyProperty(userQuestion, userQuestionDto);
            return userQuestionDto;
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
