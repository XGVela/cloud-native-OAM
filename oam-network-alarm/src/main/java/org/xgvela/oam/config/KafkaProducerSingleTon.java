package org.xgvela.oam.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaProducerSingleTon {

	private KafkaProducerSingleTon(){}
	
	private static KafkaProducer<String,String> producer;
	
	@Autowired
	private KafkaProducerProps producerProps;
	
	private static KafkaProducerProps staticProducerProps;
	
	@PostConstruct
	public void init() {
		staticProducerProps = producerProps;
		KafkaProducerHolder.instance = this;
	}
	
	public static KafkaProducerSingleTon getInstance() {
		return KafkaProducerHolder.instance;
	}
	
	private static class KafkaProducerHolder {
		private static KafkaProducerSingleTon instance = new KafkaProducerSingleTon();
	}
	
	public static KafkaProducer<String,String> getProducer() {
		if(producer == null) {
			producer = new KafkaProducer<>(getProducerProps());
		}
		return producer;
	}

	/**
	 * 独立的alonestand-producer
	 * @return
	 */
	public static Map<String,Object> getProducerProps() {
		Map<String,Object> props = new HashMap<>(16);
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, staticProducerProps.getBootstrapServerConfig());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,staticProducerProps.getKeySerializerClassConfig());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, staticProducerProps.getValueSerializerClassConfig());
		props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, staticProducerProps.getEnableIdempotenceConfig());
		props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, staticProducerProps.getMaxInFlightRequestsPerConnnection());
		props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, staticProducerProps.getMaxBlockMsConfig());
		props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, staticProducerProps.getCompressionTypeConfig());
		props.put(ProducerConfig.ACKS_CONFIG, staticProducerProps.getAcksConfig());
		props.put(ProducerConfig.RETRIES_CONFIG, staticProducerProps.getRetriesConfig());
		props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, staticProducerProps.getRetryBackoffMsConfig());
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, staticProducerProps.getBatchSizeConfig());
		props.put(ProducerConfig.LINGER_MS_CONFIG, staticProducerProps.getLingerMsConfig());
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, staticProducerProps.getBufferMemoryConfig());
		props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, staticProducerProps.getRequestTimeoutMsConfig());
		props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, staticProducerProps.getMaxRequestSizeConfig());
		return props;
	}
}
