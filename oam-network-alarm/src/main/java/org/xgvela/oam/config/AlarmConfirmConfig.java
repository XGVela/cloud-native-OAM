package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@RefreshScope
@Configuration
public class AlarmConfirmConfig {

	@Value(value = "${alarm.autoConfirm.alarmLevel}")
	private Integer alarmLevel;

	@Value(value = "${alarm.autoConfirm.alarmType}")
	private Integer alarmType;

	@Value(value = "${alarm.autoConfirm.alarmStatusType}")
	private Integer alarmStatusType;	
			
}
