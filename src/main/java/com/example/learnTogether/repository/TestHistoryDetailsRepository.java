package com.example.learnTogether.repository;

import com.example.learnTogether.models.TestHistoryDetails;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestHistoryDetailsRepository extends CrudRepository<TestHistoryDetails, Long>, JpaSpecificationExecutor<TestHistoryDetails> {
}
