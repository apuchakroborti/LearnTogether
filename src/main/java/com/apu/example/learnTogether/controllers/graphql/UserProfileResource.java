package com.apu.example.learnTogether.controllers.graphql;

import com.apu.example.learnTogether.models.UserProfile;
import com.apu.example.learnTogether.services.graphql.GraphQLService;
import graphql.ExecutionResult;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/user-profile/graphql")
@RestController
public class UserProfileResource {

    @Autowired
    GraphQLService graphQLService;

    @PostMapping()
    @Transactional
    public ResponseEntity<Object> getAllUserProfiles(@RequestBody String query){
        ExecutionResult executionResult = graphQLService.getGraphQL()
                .execute(query);
        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }
}
