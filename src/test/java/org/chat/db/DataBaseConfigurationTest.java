package org.chat.db;

import com.aerospike.client.AerospikeClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "db")
@SpringBootTest(classes = DataBaseConfiguration.class)
public class DataBaseConfigurationTest {
    @Autowired
    private GlobalCache globalCache;

    @Autowired
    private AerospikeClient aerospikeClient;

    @Autowired
    private DataBaseController dataBaseController;

    @Test
    public void testWebSocketConfiguration() {
        assertNotNull("globalCache should be created", globalCache);
        assertNotNull("aerospikeClient should be created", aerospikeClient);
        assertNotNull("dataBaseController should be created", dataBaseController);
    }
}