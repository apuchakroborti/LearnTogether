package com.apu.example.learnTogether.repository;

import com.apu.example.learnTogether.models.Comment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
}
