package org.xgvela.oam.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


@Configuration
public class ProducerKafkaConfig {


    @Value("${alarm.producerconfig.bootstrapServerConfig}")
    private String bootstrapServers;

    @Value("${alarm.producerconfig.retriesConfig}")
    private String retries;

    @Value("${alarm.producerconfig.batchSizeConfig}")
    private String batchSize;

    @Value("${alarm.producerconfig.lingerMsConfig}")
    private String lingerms;

    @Value("${alarm.producerconfig.bufferMemoryConfig}")
    private String bufferMemory;

    @Value("${alarm.producerconfig.maxBlockMsConfig}")
    private String maxBlockMsConfig;

    @Value("${alarm.producerconfig.compressionTypeConfig}")
    private String compressionType;

    @Value("${alarm.producerconfig.requestTimeoutMsConfig}")
    private String requestTimeoutmsConfig;

    @Value("${alarm.producerconfig.maxRequestSizeConfig}")
    private String maxRequestSizeConfig;

    private String keySerializerClass = "org.apache.kafka.common.serialization.StringSerializer";

    private String valueSerializerClass = "org.apache.kafka.common.serialization.StringSerializer";

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.batch-count}")
    public int batchCount;

    @Bean
    public Properties getProducerConfig() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.ACKS_CONFIG, acks);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, lingerms);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType);
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, ComstomPartitioner.class.getName());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializerClass);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializerClass);
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, maxBlockMsConfig);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeoutmsConfig);
        return props;
    }
}
