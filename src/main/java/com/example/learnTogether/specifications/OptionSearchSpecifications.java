package com.example.learnTogether.specifications;

import com.example.learnTogether.models.Option;
import com.example.learnTogether.models.Option;
import com.example.learnTogether.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class OptionSearchSpecifications {
    public static Specification<Option> withId(Long id){
        return (root, query, cb) -> id != null ?
                cb.equal(root.get("id"), id) : cb.conjunction();
    }
    public static Specification<Option> withValue(String value){
        return (root, query, cb) -> !Utils.isNullOrEmpty(value) ?
                cb.like(root.get("value"), "%" + value + "%") :
                cb.conjunction();
    }
}
