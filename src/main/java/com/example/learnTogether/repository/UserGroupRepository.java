package com.example.learnTogether.repository;

import com.example.learnTogether.models.UserGroup;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, Long>, JpaSpecificationExecutor<UserGroup> {
}
