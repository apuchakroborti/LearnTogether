package com.example.learnTogether.services.impls;

import com.example.learnTogether.dto.CommentDto;
import com.example.learnTogether.dto.request.CommentSearchCriteria;
import com.example.learnTogether.exceptions.GenericException;
import com.example.learnTogether.models.Comment;
import com.example.learnTogether.repository.CommentRepository;
import com.example.learnTogether.services.CommentService;
import com.example.learnTogether.specifications.CommentSearchSpecifications;
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
public class CommentServiceImpls implements CommentService {

    Logger logger = LoggerFactory.getLogger(CommentServiceImpls.class);

    private final CommentRepository commentRepository;

    @Autowired
    CommentServiceImpls(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(CommentDto commentDto) throws GenericException {
        try {
            Comment comment = new Comment();
            Utils.copyProperty(commentDto, comment);
            comment = commentRepository.save(comment);
            return comment;
        }catch (Exception e){
            logger.error("Exception occurred while saving, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Comment getById(Long id) throws GenericException {
        try {
            Optional<Comment> optionalComment = commentRepository.findById(id);
            return optionalComment.isPresent() ? optionalComment.get(): null;
        }catch (Exception e){
            logger.error("Exception occurred while getting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Page<Comment> getBySearchCriteria(CommentSearchCriteria criteria, Pageable pageable) throws GenericException {
        try{
            return commentRepository.findAll(CommentSearchSpecifications.withId(criteria.getId())
                            .and(CommentSearchSpecifications.withUserId(criteria.getUserProfileDto().getId()))
                            .and(CommentSearchSpecifications.withQuestionId(criteria.getUserQuestionDto().getId()))
                            .and(CommentSearchSpecifications.withStatus(criteria.getStatus()))
                    ,pageable);

        }catch (Exception e){
            logger.error("Exception occurred while getting by search criteria, message: {}", e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Comment updateById(Long id, CommentDto commentDto) throws GenericException {
        try {
            Optional<Comment> optionalComment = commentRepository.findById(id);
            if (!optionalComment.isPresent()) throw new GenericException(Defs.NOT_FOUND);

            Comment comment = new Comment();
            Utils.copyProperty(commentDto, comment);
            comment = commentRepository.save(comment);
            return comment;
        }catch (Exception e){
            logger.error("Exception occurred while updating by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteById(Long id) throws GenericException{
        try {
            Optional<Comment> optionalComment = commentRepository.findById(id);
            if(!optionalComment.isPresent()) throw new GenericException(Defs.NOT_FOUND);
            Comment comment = new Comment();
            comment.setStatus(false);
            commentRepository.save(comment);
            return true;
        }catch (Exception e){
            logger.error("Exception occurred while deleting by id: {}, message: {}",id, e.getMessage());
            throw new GenericException(e.getMessage());
        }
    }
}
