package org.home.db;

import com.aerospike.client.AerospikeClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Profile("db")
@Configuration
@EnableScheduling
@EnableConfigurationProperties(DataBaseProperties.class)
public class DataBaseConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public GlobalCache globalCache() {
        return new GlobalCache();
    }

    @Bean
    @ConditionalOnMissingBean
    public AerospikeClient aerospikeClient(DataBaseProperties dataBaseProperties) {
        return new AerospikeClient(dataBaseProperties.getIp(), dataBaseProperties.getPort());
    }

    @Bean
    @ConditionalOnMissingBean
    public DataBaseManager dataBaseManager(GlobalCache globalCache, AerospikeClient aerospikeClient) {
        return new DataBaseManager(globalCache, aerospikeClient);
    }
}