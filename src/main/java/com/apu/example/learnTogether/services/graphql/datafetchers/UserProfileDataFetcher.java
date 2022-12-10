package com.apu.example.learnTogether.services.graphql.datafetchers;

import com.apu.example.learnTogether.models.UserProfile;
import com.apu.example.learnTogether.repository.UserProfileRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserProfileDataFetcher implements DataFetcher<UserProfile> {
    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public UserProfile get(DataFetchingEnvironment dataFetchingEnvironment) {
        String email = dataFetchingEnvironment.getArgument("email");
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByEmail(email);
        if(optionalUserProfile.isPresent())return optionalUserProfile.get();
        return null;
    }
}
