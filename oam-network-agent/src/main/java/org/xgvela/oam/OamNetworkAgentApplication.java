package org.xgvela.oam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "org.xgvela.oam.config",
        "org.xgvela.oam.service",
        "org.xgvela.oam.controller",
        "org.xgvela.oam.grpc",
        "org.xgvela.oam.utils",
        "com.inspur.cnet.security",
        "com.inspur.cnet.system"
})
@MapperScan(basePackages = {"org.xgvela.oam.log.mapper", "org.xgvela.oam.mapper", "com.inspur.cnet.security.mapper",
        "com.inspur.cnet.system.mapper"})
public class OamNetworkAgentApplication {
    public static void main(String[] args) {
        SpringApplication.run(OamNetworkAgentApplication.class, args);
    }
}
