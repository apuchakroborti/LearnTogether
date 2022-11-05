package com.example.learnTogether.repository;

import com.example.learnTogether.models.TestHistory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestHistoryRepository extends CrudRepository<TestHistory, Long>, JpaSpecificationExecutor<TestHistory> {
}
