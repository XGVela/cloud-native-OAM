package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "heartbeat")
public class HeartBeatConfiguration {
    private String host;
    private Integer port;
}
