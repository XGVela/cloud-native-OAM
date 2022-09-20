package org.xgvela.oam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = {
        "org.xgvela.oam.config",
        "org.xgvela.oam.service",
        "org.xgvela.oam.controller",
        "org.xgvela.oam.utils",
        "com.inspur.cnet.security",
        "com.inspur.cnet.system",
        "com.inspur.cnet.security.service"
})
@MapperScan(basePackages = {"org.xgvela.oam.log.mapper", "org.xgvela.oam.mapper", "com.inspur.cnet.security.mapper",
        "com.inspur.cnet.system.mapper"})
public class OamNetworkLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(OamNetworkLogApplication.class, args);
    }
}
