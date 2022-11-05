package com.example.learnTogether.repository;

import com.example.learnTogether.models.Option;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long>, JpaSpecificationExecutor<Option> {
}
