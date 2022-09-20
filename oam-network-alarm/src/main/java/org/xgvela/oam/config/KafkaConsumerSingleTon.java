package org.xgvela.oam.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Component
public class KafkaConsumerSingleTon {

	@Autowired
	private KafkaConsumerProps consumerProps;

	private static KafkaConsumerProps staticKafkaConsumerProps;

	private KafkaConsumerSingleTon() {
	}

	private static KafkaConsumer<String, String> consumer;

	@PostConstruct
	public void init() {
		staticKafkaConsumerProps = consumerProps;
		KafkaConsumerHolder.instance = this;
	}

	private static class KafkaConsumerHolder {
		private static KafkaConsumerSingleTon instance = new KafkaConsumerSingleTon();
	}

	public static KafkaConsumerSingleTon getInstance() {
		return KafkaConsumerHolder.instance;
	}

	public static KafkaConsumer<String, String> getConsumer() {
		if (consumer == null) {
			consumer = new KafkaConsumer<>(getConsumerProps());
		}
		return consumer;
	}


	/**
	 * Stand-alone lonestand-consumer
	 * @return
	 */
	public static Map<String, Object> getConsumerProps() {
		Map<String, Object> props = new HashMap<>(16);
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, staticKafkaConsumerProps.getBootstrapServerConfig());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, staticKafkaConsumerProps.getKeyDeserializerClassConfig());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, staticKafkaConsumerProps.getValueDeserializerClassConfig());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, staticKafkaConsumerProps.getGroupIdConfig());

		props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, staticKafkaConsumerProps.getMaxPollIntervalMsConfig());
		props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, staticKafkaConsumerProps.getRequestTimeoutMsConfig());

		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, staticKafkaConsumerProps.getMaxPollRecordsConfig());

		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, staticKafkaConsumerProps.getAutoOffsetResetConfig());

		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, staticKafkaConsumerProps.getEnableAutoCommitConfig());

		props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, staticKafkaConsumerProps.getFetchMaxBytesConfig());

		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, staticKafkaConsumerProps.getSessionTimeoutMsConfig());

		props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, staticKafkaConsumerProps.getHeartBeatIntervalMsConfig());

		props.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, staticKafkaConsumerProps.getConnectionsMaxIdleMsConfig());

		props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1);

		return props;
	}
	
	@Bean("batchContainerFactory")
    public ConcurrentKafkaListenerContainerFactory listenerContainer() {
        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(new DefaultKafkaConsumerFactory<>(KafkaConsumerSingleTon.getConsumerProps()));
        container.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        container.setConcurrency(1);

        container.setAutoStartup(false);

        container.setBatchListener(true);
        return container;
    }
	
	@Bean("batchHandleAlarmAndWebsocket")
    public ConcurrentKafkaListenerContainerFactory handleListenerContainer() {
        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(new DefaultKafkaConsumerFactory<>(KafkaConsumerSingleTon.getConsumerProps()));
        container.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        container.setConcurrency(1);

        container.setAutoStartup(false);

        container.setBatchListener(true);
        return container;
    }

	@Bean("batchAlarmAnalysisContainerFactory")
	public ConcurrentKafkaListenerContainerFactory listenerAlarmAnalysisContainer() {
		ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
		container.setConsumerFactory(new DefaultKafkaConsumerFactory<>(KafkaConsumerSingleTon.getConsumerProps()));
		container.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

		container.setConcurrency(1);

		container.setAutoStartup(false);

		container.setBatchListener(true);
		return container;
	}

}
