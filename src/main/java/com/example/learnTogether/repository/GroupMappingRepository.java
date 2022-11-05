package com.example.learnTogether.repository;

import com.example.learnTogether.models.UserGroupMapping;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMappingRepository extends CrudRepository<UserGroupMapping, Long>, JpaSpecificationExecutor<UserGroupMapping> {
}
