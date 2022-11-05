package com.example.learnTogether.repository;

import com.example.learnTogether.models.Country;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long>, JpaSpecificationExecutor<Country> {
}
