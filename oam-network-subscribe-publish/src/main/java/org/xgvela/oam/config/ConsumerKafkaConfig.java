package org.xgvela.oam.config;

import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


@Configuration
@Data
public class ConsumerKafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${spring.kafka.consumer.auto-offset-reset}")
	private String autoOffSetReset;
	
	@Value("${spring.kafka.consumer.auto-commit-interval}")
	private String autoCommitInterval;
	
	@Value("${spring.kafka.consumer.enable-auto-commit}")
	private boolean enableAutoCommit;

	@Value("${oam.subscribe.consumerGroupId}")
	private String consumerGroupId;

	@Value("${spring.kafka.consumer.isolationLevelConfig}")
	private String isolationLevelConfig;
	
	private String keySerializerClass = "org.apache.kafka.common.serialization.StringDeserializer";
	
	private String valueSerializerClass = "org.apache.kafka.common.serialization.StringDeserializer";
	
	@Bean
	public Properties getConsumerConfig() {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, isolationLevelConfig);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffSetReset);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keySerializerClass);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueSerializerClass);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
		return props;
	}
}
