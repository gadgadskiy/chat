package org.chat.db;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "db")
public class DataBaseProperties {
    private String ip;
    private int port;
}