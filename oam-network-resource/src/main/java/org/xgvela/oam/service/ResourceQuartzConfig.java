package org.xgvela.oam.service;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xgvela.oam.config.ResourceConfig;

import javax.annotation.Resource;

@Configuration
public class ResourceQuartzConfig {

    @Resource
    private ResourceConfig resourceConfig;

    @Bean
    public JobDetail resourceJobDetail(){
        return JobBuilder.newJob(ResourceService.class)
                .withIdentity("ResourceService")
                .withDescription("ResourceFile")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger nfRegisterTrigger(){
        return TriggerBuilder.newTrigger()
                .forJob(resourceJobDetail())
                .startNow()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(resourceConfig.getDownloadResourceInterval()))
                .build();
    }
}
