package com.apu.example.learnTogether.specifications;

import com.apu.example.learnTogether.models.TestHistoryDetails;
import org.springframework.data.jpa.domain.Specification;

public class TestHistoryDetailsSearchSpecifications {
    public static Specification<TestHistoryDetails> withId(Long id){
        return (root, query, cb) -> id != null ? cb.equal(root.get("id"), id) : cb.conjunction();
    }
    public static Specification<TestHistoryDetails> withTestHistoryId(Long testHistoryId){
        return (root, query, cb) -> testHistoryId != null ?
                cb.equal(root.join("testHistory").get("id"), testHistoryId) : cb.conjunction();
    }
    public static Specification<TestHistoryDetails> withQuestionId(Long questionId){
        return (root, query, cb) -> questionId != null ?
                cb.equal(root.join("userQuestion").get("id"), questionId) : cb.conjunction();
    }
}
