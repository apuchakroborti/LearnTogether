package com.apu.example.learnTogether.specifications;

import com.apu.example.learnTogether.models.District;
import com.apu.example.learnTogether.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class DistrictSearchSpecifications {
    public static Specification<District> withId(Long id){
        return (root, query, cb) -> id != null ?
                cb.equal(root.get("id"), id) : cb.conjunction();
    }
    public static Specification<District> withName(String name){
        return (root, query, cb) -> !Utils.isNullOrEmpty(name) ?
                cb.like(root.get("name"), "%" + name + "%") :
                cb.conjunction();
    }
    public static Specification<District> withCode(String code){
        return (root, query, cb) -> !Utils.isNullOrEmpty(code) ?
                cb.equal(root.get("code"),  code ) :
                cb.conjunction();
    }
}
