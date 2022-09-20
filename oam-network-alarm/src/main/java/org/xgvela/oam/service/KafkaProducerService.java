package org.xgvela.oam.service;

import org.xgvela.oam.config.ProducerKafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * kafka
 * @author maopu
 * @since 2021/11/11
 */
@Slf4j
@Component
public class KafkaProducerService<K, V> {

    @Autowired
    private ProducerKafkaConfig kafkaProducerConfig;

    Producer<K, V> producer = null;

    ProducerRecord<K, V> record = null;

    private static class SingletonHolder {

        private static KafkaProducerService instance = new KafkaProducerService();
    }

    public static KafkaProducerService<Object, Object> getInstance() {
        return SingletonHolder.instance;
    }

    @PostConstruct
    public void init() {
        SingletonHolder.instance = this;
        producer = new KafkaProducer<>(kafkaProducerConfig.getProducerConfig());
    }

    public void send(String topic, V message) {

        try {
            if (producer == null) {
                producer = new KafkaProducer<>(kafkaProducerConfig.getProducerConfig());
            }
            record = new ProducerRecord<>(topic, message);
            producer.send(record);
        } catch (Exception e) {
            log.error("KafkaProducerService to kafka error:{}, producer:{}, topic:{}, key{}, json:{}", e,
                    producer, topic, message);
        }

    }
}
