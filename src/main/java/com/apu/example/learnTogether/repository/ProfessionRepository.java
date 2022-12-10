package com.apu.example.learnTogether.repository;

import com.apu.example.learnTogether.models.Profession;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends CrudRepository<Profession, Long>, JpaSpecificationExecutor<Profession> {
}
