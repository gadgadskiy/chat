package org.chat.db;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.IAerospikeClient;
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
    public IAerospikeClient aerospikeClient(DataBaseProperties dataBaseProperties) {
        return new AerospikeClient(dataBaseProperties.getIp(), dataBaseProperties.getPort());
    }

    @Bean
    @ConditionalOnMissingBean
    public DataBaseController dataBaseController(GlobalCache globalCache, IAerospikeClient aerospikeClient) {
        return new DataBaseController(globalCache, aerospikeClient);
    }
}