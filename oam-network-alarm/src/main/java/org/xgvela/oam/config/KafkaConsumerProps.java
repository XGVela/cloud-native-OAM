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

	/**
	 * The user's consumption of messages takes the most time to process, the business processing time，120s
	 */
	@Value("${alarm.consumerconfig.maxPollIntervalMsConfig}")
	private int maxPollIntervalMsConfig;

	@Value("${alarm.consumerconfig.requestTimeoutMsConfig}")
	private int requestTimeoutMsConfig;

	/**
	 *  If the consumer poll speed is too slow, reduce the speed of this value appropriately
	 */
	@Value("${alarm.consumerconfig.maxPollRecordsConfig}")
	private int maxPollRecordsConfig;

	/**
	 * Specify the earliest displacement consumption, the earliest is not necessarily 0, after the group restart, from the beginning of the consumption
	 */
	@Value("${alarm.consumerconfig.autoOffsetResetConfig}")
	private String autoOffsetResetConfig;

	/**
	 * Manually control autocommit
	 */
	@Value("${alarm.consumerconfig.enableAutoCommitConfig}")
	private String enableAutoCommitConfig;

	/**
	 * consumer The maximum number of bytes of data fetched in a single pass，16MB数据
	 */
	@Value("${alarm.consumerconfig.fetchMaxBytesConfig}")
	private int fetchMaxBytesConfig;

	/**
	 * Detecting consumers detaches from the group, the consumer's crash detection
	 */
	@Value("${alarm.consumerconfig.sessionTimeoutMsConfig}")
	private int sessionTimeoutMsConfig;

	/**
	 * How other members of the consumer group learned about the start of a new round of realaction
	 */
	@Value("${alarm.consumerconfig.heartBeatIntervalMsConfig}")
	private int heartBeatIntervalMsConfig;

	/**
	 * Periodic observation requests spike in average processing time, periodic consumer and broker socket channels, and -1 indicates that idle connections are not closed
	 */
	@Value("${alarm.consumerconfig.connectionsMaxIdleMsConfig}")
	private long connectionsMaxIdleMsConfig;
}
