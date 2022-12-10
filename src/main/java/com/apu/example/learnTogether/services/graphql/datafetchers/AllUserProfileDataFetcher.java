package com.apu.example.learnTogether.services.graphql.datafetchers;

import com.apu.example.learnTogether.models.UserProfile;
import com.apu.example.learnTogether.repository.UserProfileRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AllUserProfileDataFetcher implements DataFetcher<List<UserProfile>> {

    @Autowired
    UserProfileRepository userProfileRepository;


    @Override
    public List<UserProfile> get(DataFetchingEnvironment dataFetchingEnvironment) {

        return userProfileRepository.findAll();
    }
}
