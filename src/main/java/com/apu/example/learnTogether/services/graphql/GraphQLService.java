package com.apu.example.learnTogether.services.graphql;

import com.apu.example.learnTogether.services.graphql.datafetchers.AllUserProfileDataFetcher;
import com.apu.example.learnTogether.services.graphql.datafetchers.DistrictDataFetcher;
import com.apu.example.learnTogether.services.graphql.datafetchers.UserProfileDataFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {
    @Value("classpath:learnTogether.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllUserProfileDataFetcher allUserProfileDataFetchers;

    @Autowired
    private UserProfileDataFetcher userProfile;

    @Autowired
    private DistrictDataFetcher districtDataFetcher;

    @PostConstruct
    public void loadSchema() throws IOException{
        //get the schema
        File schemaFile = resource.getFile();

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser()
                .parse(schemaFile);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator()
                .makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }
    private RuntimeWiring buildRuntimeWiring(){
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring ->
                    typeWiring
                            .dataFetcher("getAllUserProfiles", allUserProfileDataFetchers)
                            .dataFetcher("findUserProfile", userProfile)
                            .dataFetcher("getAllDistricts", districtDataFetcher)
                ).build();
    }
    public GraphQL getGraphQL(){
        return graphQL;
    }

}
