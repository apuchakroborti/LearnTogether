package com.apu.example.learnTogether.specifications;

import com.apu.example.learnTogether.models.UserGroupMapping;
import org.springframework.data.jpa.domain.Specification;

public class GroupMappingSearchSpecifications {
    public static Specification<UserGroupMapping> withId(Long id){
        return (root, query, cb) -> id != null ? cb.equal(root.get("id"), id) : cb.conjunction();
    }

    public static Specification<UserGroupMapping> withUserId(Long userId){
        return (root, query, cb) -> userId != null ?
                cb.equal(root.join("userProfile").get("id"), userId) : cb.conjunction();
    }
    public static Specification<UserGroupMapping> withGroupId(Long groupId){
        return (root, query, cb) -> groupId != null ?
                cb.equal(root.join("userGroup").get("id"), groupId) : cb.conjunction();
    }
}
