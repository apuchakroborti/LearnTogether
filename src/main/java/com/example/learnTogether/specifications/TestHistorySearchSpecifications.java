package com.example.learnTogether.specifications;

import com.example.learnTogether.models.TestHistory;
import com.example.learnTogether.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class TestHistorySearchSpecifications {
    public static Specification<TestHistory> withId(Long id){
        return (root, query, cb) -> id != null ?
                cb.equal(root.get("id"), id) : cb.conjunction();
    }

    public static Specification<TestHistory> withName(String name){
        return (root, query, cb) -> !Utils.isNullOrEmpty(name) ?
                cb.like(root.get("name"), "%"+name+"%") :
                cb.conjunction();
    }
    public static Specification<TestHistory> withCode(String code){
        return (root, query, cb) -> !Utils.isNullOrEmpty(code) ?
                cb.equal(root.get("code"), code) :
                cb.conjunction();
    }
}
