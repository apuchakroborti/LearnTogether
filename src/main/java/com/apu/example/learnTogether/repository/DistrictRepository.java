package com.apu.example.learnTogether.repository;

import com.apu.example.learnTogether.models.District;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends CrudRepository<District, Long>, JpaSpecificationExecutor<District> {
}
