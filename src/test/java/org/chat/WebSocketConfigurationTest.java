package org.chat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebSocketConfiguration.class)
public class WebSocketConfigurationTest {
    @Autowired
    private WebSocketConfiguration webSocketConfiguration;

    @Test
    public void testWebSocketConfiguration() {
        assertNotNull("webSocketConfiguration should be created", webSocketConfiguration);
    }
}