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
        "org.xgvela.oam.utils"
})
@MapperScan(basePackages = {"org.xgvela.oam.log.mapper", "org.xgvela.oam.mapper", "org.xgvela.cnet.security.mapper",
        "org.xgvela.cnet.system.mapper"})
public class OamNetworkNfTubeApplication {
    public static void main(String[] args) {
        SpringApplication.run(OamNetworkNfTubeApplication.class, args);
    }
}
