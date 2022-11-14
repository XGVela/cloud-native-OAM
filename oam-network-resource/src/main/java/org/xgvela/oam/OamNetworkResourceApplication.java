package org.xgvela.oam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "org.xgvela.oam.service",
        "org.xgvela.oam.utils",
        "org.xgvela.oam.entity",
        "org.xgvela.oam.config",
})
@MapperScan(basePackages = {
        "org.xgvela.oam.config",
        "org.xgvela.oam.log.mapper",
        "org.xgvela.oam.mapper"
})
public class OamNetworkResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OamNetworkResourceApplication.class, args);
    }
}
