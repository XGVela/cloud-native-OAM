package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "config")
public class ConfigConfiguration {
    private String agentHost;
    private Integer agentPort;
}
