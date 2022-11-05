package com.example.learnTogether.specifications;

import com.example.learnTogether.models.Comment;
import com.example.learnTogether.models.Comment;
import com.example.learnTogether.models.Comment;
import com.example.learnTogether.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class CommentSearchSpecifications {
    public static Specification<Comment> withId(Long id){
        return (root, query, cb) -> id != null ? cb.equal(root.get("id"), id) : cb.conjunction();
    }

    public static Specification<Comment> withDescription(String description){
        return (root, query, cb) -> !Utils.isNullOrEmpty(description) ?
                cb.like(root.get("description"), "%"+description+"%") :
                cb.conjunction();
    }

    public static Specification<Comment> withUserId(Long userId){
        return (root, query, cb) -> userId != null ?
                cb.equal(root.join("userProfile").get("id"), userId) : cb.conjunction();
    }
    public static Specification<Comment> withQuestionId(Long questionId){
        return (root, query, cb) -> questionId != null ?
                cb.equal(root.join("question").get("id"), questionId) : cb.conjunction();
    }
    public static Specification<Comment> withStatus(Boolean status){
        return (root, query, cb) -> status!=null ?
                cb.equal(root.get("status"), status) :
                cb.conjunction();
    }
}
