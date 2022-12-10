package com.apu.example.learnTogether.repository;


import com.apu.example.learnTogether.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(String name);
}