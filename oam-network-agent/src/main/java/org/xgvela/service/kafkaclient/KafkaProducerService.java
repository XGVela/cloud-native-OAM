package org.xgvela.service.kafkaclient;

import org.xgvela.config.ProducerKafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class KafkaProducerService<K,V> {

    @Autowired
    private ProducerKafkaConfig producerKafkaConfig;

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
        producer = new KafkaProducer<>(producerKafkaConfig.getProducerConfig());
    }

    public boolean send(String topic, V message) {
        try {
            if (producer == null) {
                producer = new KafkaProducer<>(producerKafkaConfig.getProducerConfig());
            }
            record = new ProducerRecord<>(topic, message);
            producer.send(record);
            return true;
        } catch (Exception e) {
            log.error("KafkaProducerService to kafka error:{}, producer:{}, topic:{}, key{}, json:{}", e, producer, topic, message);
        }
        return false;
    }

}
