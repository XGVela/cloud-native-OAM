package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class KafkaProducerProps {

	@Value("${alarm.producerconfig.bootstrapServerConfig}")
	private String bootstrapServerConfig;

	@Value("${alarm.producerconfig.keySerializerClassConfig}")
	private String keySerializerClassConfig;

	@Value("${alarm.producerconfig.valueSerializerClassConfig}")
	private String valueSerializerClassConfig;

	@Value("${alarm.producerconfig.enableIdempotenceConfig}")
	private String enableIdempotenceConfig;

	@Value("${alarm.producerconfig.maxInFlightRequestsPerConnnection}")
	private int maxInFlightRequestsPerConnnection;

	@Value("${alarm.producerconfig.maxBlockMsConfig}")
	private long maxBlockMsConfig;

	@Value("${alarm.producerconfig.compressionTypeConfig}")
	private String compressionTypeConfig;

	@Value("${alarm.producerconfig.acksConfig}")
	private String acksConfig;

	@Value("${alarm.producerconfig.retriesConfig}")
	private int retriesConfig;

	@Value("${alarm.producerconfig.retryBackoffMsConfig}")
	private long retryBackoffMsConfig;

	@Value("${alarm.producerconfig.batchSizeConfig}")
	private int batchSizeConfig;

	@Value("${alarm.producerconfig.lingerMsConfig}")
	private long lingerMsConfig;

	@Value("${alarm.producerconfig.bufferMemoryConfig}")
	private long bufferMemoryConfig;

	@Value("${alarm.producerconfig.requestTimeoutMsConfig}")
	private int requestTimeoutMsConfig;

	@Value("${alarm.producerconfig.maxRequestSizeConfig}")
	private int maxRequestSizeConfig;
}
