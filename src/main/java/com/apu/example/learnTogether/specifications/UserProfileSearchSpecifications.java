package com.apu.example.learnTogether.specifications;

import com.apu.example.learnTogether.models.UserProfile;
import com.apu.example.learnTogether.utils.Utils;
import org.springframework.data.jpa.domain.Specification;

public class UserProfileSearchSpecifications {
    public static Specification<UserProfile> withId(Long id){
        return (root, query, cb) -> id != null ? cb.equal(root.get("id"), id) : cb.conjunction();
    }
    public static Specification<UserProfile> withFirstName(String firstName){
        return (root, query, cb) -> !Utils.isNullOrEmpty(firstName) ?
                cb.like(root.get("firstName"), "%" + firstName + "%") :
                cb.conjunction();
    }
    public static Specification<UserProfile> withLastName(String lastName){
        return (root, query, cb) -> !Utils.isNullOrEmpty(lastName) ?
                cb.like(root.get("lastName"), "%" + lastName + "%") :
                cb.conjunction();
    }
    public static Specification<UserProfile> withPhone(String phone){
        return (root, query, cb) -> !Utils.isNullOrEmpty(phone) ?
                cb.equal(root.get("phone"), phone) :
                cb.conjunction();
    }
    public static Specification<UserProfile> withEmail(String email){
        return (root, query, cb) -> !Utils.isNullOrEmpty(email) ?
                cb.equal(root.get("email"), email) :
                cb.conjunction();
    }
    public static Specification<UserProfile> withStatus(Boolean status){
        return (root, query, cb) -> status!=null ?
                cb.equal(root.get("status"), status) :
                cb.conjunction();
    }
}
