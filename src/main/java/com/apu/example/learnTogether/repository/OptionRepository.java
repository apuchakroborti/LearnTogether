package com.apu.example.learnTogether.repository;

import com.apu.example.learnTogether.models.Option;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long>, JpaSpecificationExecutor<Option> {
    Optional<Option> findByValue(String value);
}
