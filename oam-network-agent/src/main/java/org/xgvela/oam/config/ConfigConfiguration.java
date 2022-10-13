package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ConfigConfiguration {

    @Value("${agent.config.client.host}")
    private String  agentConfigClientHost ;

    @Value("${agent.config.client.port}")
    private String agentConfigClientPort;
}
