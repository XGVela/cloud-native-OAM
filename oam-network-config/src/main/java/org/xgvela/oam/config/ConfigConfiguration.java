package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "config")
public class ConfigConfiguration {
    //config.agent.client.host
    //config.agent.client.port
    private String agentHost;
    private Integer agentPort;
}
