package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
public class KafkaConsumerProps {

	@Value("${alarm.consumerconfig.bootstrapServerConfig}")
	private String bootstrapServerConfig;

	@Value("${alarm.consumerconfig.keyDeserializerClassConfig}")
	private String keyDeserializerClassConfig;

	@Value("${alarm.consumerconfig.valueDeserializerClassConfig}")
	private String valueDeserializerClassConfig;

	@Value("${alarm.consumerconfig.groupIdConfig}")
	private String groupIdConfig;

	@Value("${alarm.consumerconfig.maxPollIntervalMsConfig}")
	private int maxPollIntervalMsConfig;

	@Value("${alarm.consumerconfig.requestTimeoutMsConfig}")
	private int requestTimeoutMsConfig;

	@Value("${alarm.consumerconfig.maxPollRecordsConfig}")
	private int maxPollRecordsConfig;

	@Value("${alarm.consumerconfig.autoOffsetResetConfig}")
	private String autoOffsetResetConfig;

	@Value("${alarm.consumerconfig.enableAutoCommitConfig}")
	private String enableAutoCommitConfig;

	@Value("${alarm.consumerconfig.fetchMaxBytesConfig}")
	private int fetchMaxBytesConfig;

	@Value("${alarm.consumerconfig.sessionTimeoutMsConfig}")
	private int sessionTimeoutMsConfig;

	@Value("${alarm.consumerconfig.heartBeatIntervalMsConfig}")
	private int heartBeatIntervalMsConfig;

	@Value("${alarm.consumerconfig.connectionsMaxIdleMsConfig}")
	private long connectionsMaxIdleMsConfig;
}
