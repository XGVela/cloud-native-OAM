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

	/**
	 * Enables the idempotency of a single partition of producer to ensure that there are no duplicate messages in a single partition
	 */
	@Value("${alarm.producerconfig.enableIdempotenceConfig}")
	private String enableIdempotenceConfig;

	/**
	 * The producer ensures that only one request can be sent at a time
	 */
	@Value("${alarm.producerconfig.maxInFlightRequestsPerConnnection}")
	private int maxInFlightRequestsPerConnnection;

	/**
	 * When the buffer is full, the producer is in a blocked state to stop receiving new requests instead of throwing an exception, otherwise producer production is too fast to cause the buffer to run out and report an error
	 */
	@Value("${alarm.producerconfig.maxBlockMsConfig}")
	private long maxBlockMsConfig;

	/**
	 * The use of compression algorithm makes the producer write faster, increasing the CPU consumption of the machine
	 */
	@Value("${alarm.producerconfig.compressionTypeConfig}")
	private String compressionTypeConfig;

	/**
	 * There is one copy successfully written to the log, one leader + one follower
	 */
	@Value("${alarm.producerconfig.acksConfig}")
	private String acksConfig;

	/**
	 * Send failed, retry 30 times
	 */
	@Value("${alarm.producerconfig.retriesConfig}")
	private int retriesConfig;

	/**
	 * The leader election time determines the retry time
	 */
	@Value("${alarm.producerconfig.retryBackoffMsConfig}")
	private long retryBackoffMsConfig;

	/**
	 * Multiple messages sent to the same partition are encapsulated in a batch, and when the fetch is full or the waiting time is up, the message is sent, a 1MB message
	 */
	@Value("${alarm.producerconfig.batchSizeConfig}")
	private int batchSizeConfig;

	/**
	 * If the batch does not reach 1M, but the delay is 100ms, the data in the buffer is sent as a message
	 */
	@Value("${alarm.producerconfig.lingerMsConfig}")
	private long lingerMsConfig;

	/**
	 * The buffer size used by the producer side to cache messages, the default value is 32M
	 */
	@Value("${alarm.producerconfig.bufferMemoryConfig}")
	private long bufferMemoryConfig;

	/**
	 * Producer sends a broker message, and the processing message needs to be returned to the producer response within 60s within a specified time
	 */
	@Value("${alarm.producerconfig.requestTimeoutMsConfig}")
	private int requestTimeoutMsConfig;

	/**
	 * The maximum message size that can be sent by the producer
	 */
	@Value("${alarm.producerconfig.maxRequestSizeConfig}")
	private int maxRequestSizeConfig;
	
}
