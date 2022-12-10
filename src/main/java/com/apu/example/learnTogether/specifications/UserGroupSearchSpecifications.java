package com.apu.example.learnTogether.specifications;

import com.apu.example.learnTogether.models.UserGroup;
import com.apu.example.learnTogether.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class UserGroupSearchSpecifications {
    public static Specification<UserGroup> withId(Long id){
        return (root, query, cb) -> id != null ? cb.equal(root.get("id"), id) : cb.conjunction();
    }
    public static Specification<UserGroup> withGroupName(String groupName){
        return (root, query, cb) -> !Utils.isNullOrEmpty(groupName) ?
                cb.like(root.get("groupName"), "%" + groupName + "%") :
                cb.conjunction();
    }
    public static Specification<UserGroup> withStatus(Boolean status){
        return (root, query, cb) -> status!=null ?
                cb.equal(root.get("status"), status) :
                cb.conjunction();
    }
}
