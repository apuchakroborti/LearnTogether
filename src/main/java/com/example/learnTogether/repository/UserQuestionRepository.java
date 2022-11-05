package com.example.learnTogether.repository;

import com.example.learnTogether.models.UserQuestion;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestionRepository extends CrudRepository<UserQuestion, Long>, JpaSpecificationExecutor<UserQuestion> {
}
