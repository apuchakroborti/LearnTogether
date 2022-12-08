package com.example.learnTogether.config;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazleCastCacheConfig {

     @Bean
    public Config configure() {
        return new Config().setInstanceName("hazlecast-insatnce")
                .addMapConfig(
                        new MapConfig()
                        .setName("userCache")
                        .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                        .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(20));

    }

}
