package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class QyWexinConfig {

    @Value("${omc.message.qyweixin.corpid}")
    private String corpid;

    @Value("${omc.message.qyweixin.corpsecret}")
    private String corpsecret;

    @Value("${omc.message.qyweixin.agentid}")
    private Long agentid;

    @Value("${omc.message.qyweixin.url}")
    private String url;

}
