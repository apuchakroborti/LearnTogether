package com.example.learnTogether.specifications;

import com.example.learnTogether.models.UserQuestion;
import com.example.learnTogether.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class UserQuestionSearchSpecifications {
    public static Specification<UserQuestion> withId(Long id){
        return (root, query, cb) -> id != null ? cb.equal(root.get("id"), id) : cb.conjunction();
    }

    public static Specification<UserQuestion> withUserId(Long userId){
        return (root, query, cb) -> userId != null ?
                cb.equal(root.join("userProfile").get("id"), userId) : cb.conjunction();
    }

    public static Specification<UserQuestion> withDescription(String description){
        return (root, query, cb) -> !Utils.isNullOrEmpty(description) ?
                cb.like(root.get("description"), "%"+description+"%") :
                cb.conjunction();
    }
    public static Specification<UserQuestion> withStatus(Boolean status){
        return (root, query, cb) -> status!=null ?
                cb.equal(root.get("status"), status) :
                cb.conjunction();
    }
}
