package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "resource")
public class ResourceConfig {
    //#hour
    private Integer downloadResourceInterval;

}
