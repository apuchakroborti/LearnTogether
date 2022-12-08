package com.example.learnTogether.config;

import com.hazelcast.cache.HazelcastCacheManager;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazleCastCacheConfig {

    /*public static final String USER_CACHE = "userCache";
    private final HazelcastInstance hazelcastInstance
            = Hazelcast.newHazelcastInstance(createConfig());

    public Config createConfig() {
        Config config = new Config();
        config.addMapConfig(mapConfig());
        return config;
    }

    private MapConfig mapConfig() {
        MapConfig mapConfig = new MapConfig(USER_CACHE);
        mapConfig.setTimeToLiveSeconds(360);
        mapConfig.setMaxIdleSeconds(20);
        return mapConfig;
    }*/

    /*@Bean
    public Config configure() {
        *//*return new Config().setInstanceName("hazlecast-insatnce")
                .addMapConfig(
                        new MapConfig()
                        .setName("userCache")
                        .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                        .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(2000));*//*
        return new Config()
                .setInstanceName("hazlecast-insatnce")
                .addMapConfig(
                        new MapConfig()
                        .setName("userCache")
                        .setEvictionConfig(new EvictionConfig()
                            .setEvictionPolicy(EvictionPolicy.LRU)
                        )
                );
    }*/

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
