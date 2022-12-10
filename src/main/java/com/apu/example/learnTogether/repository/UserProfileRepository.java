package com.apu.example.learnTogether.repository;

import com.apu.example.learnTogether.models.UserProfile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile> {

    Optional<UserProfile> findByEmail(String email);
    Optional<UserProfile> findByUserId(String userId);
    @Query("select  cu from UserProfile cu where cu.oauthUser.id = ?#{principal.id}")
    Optional<UserProfile> getLoggedInEmployee();

    @Query("SELECT coalesce(max(user.id), 0) FROM UserProfile user")
    Long getMaxId();

}
