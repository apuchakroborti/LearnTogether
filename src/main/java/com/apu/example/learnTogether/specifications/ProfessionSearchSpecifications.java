package com.apu.example.learnTogether.specifications;

import com.apu.example.learnTogether.models.Profession;
import com.apu.example.learnTogether.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class ProfessionSearchSpecifications {
    public static Specification<Profession> withId(Long id){
        return (root, query, cb) -> id != null ?
                cb.equal(root.get("id"), id) : cb.conjunction();
    }
    public static Specification<Profession> withName(String name){
        return (root, query, cb) -> !Utils.isNullOrEmpty(name) ?
                cb.like(root.get("name"), "%" + name + "%") :
                cb.conjunction();
    }
}
