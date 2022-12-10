package com.apu.example.learnTogether.services.graphql.datafetchers;

import com.apu.example.learnTogether.models.District;
import com.apu.example.learnTogether.repository.DistrictRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DistrictDataFetcher implements DataFetcher<List<District>> {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> get(DataFetchingEnvironment environment) {
        return districtRepository.findAll();
    }
}
