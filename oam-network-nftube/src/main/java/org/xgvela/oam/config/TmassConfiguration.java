package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "tmass")
public class TmassConfiguration {
    private String ip;
    private Integer port;
    private String requestPath;
}
