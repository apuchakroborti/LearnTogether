package com.example.learnTogether.specifications;

import com.example.learnTogether.models.Country;
import com.example.learnTogether.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class CountrySearchSpecifications {
    public static Specification<Country> withId(Long id){
        return (root, query, cb) -> id != null ?
                cb.equal(root.get("id"), id) : cb.conjunction();
    }
    public static Specification<Country> withName(String name){
        return (root, query, cb) -> !Utils.isNullOrEmpty(name) ?
                cb.like(root.get("name"), "%" + name + "%") :
                cb.conjunction();
    }
    public static Specification<Country> withCode(String code){
        return (root, query, cb) -> !Utils.isNullOrEmpty(code) ?
                cb.equal(root.get("code"),  code ) :
                cb.conjunction();
    }
}
