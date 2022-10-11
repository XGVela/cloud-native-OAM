package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
public class ESConfig {

    @Value("${elasticsearch.url}")
    public String url;

    @Value("${elasticsearch.port}")
    public Integer port;
}
