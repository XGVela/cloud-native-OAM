package org.xgvela.oam.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author maopu
 * @since 2021/11/11
 */
public class ComstomPartitioner implements Partitioner {

    private final ConcurrentMap<String, AtomicInteger> topicCounterMap = new ConcurrentHashMap<>();

    @Override
    public void configure(Map<String, ?> configs) {
    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);

        int numberOfPartitions = partitions.size();

        if (keyBytes == null) {
            int partitionNumber = this.nextPartition(topic);
            List<PartitionInfo> availablePartitions = cluster.availablePartitionsForTopic(topic);
            if (!availablePartitions.isEmpty()) {
                int part = Utils.toPositive(partitionNumber) % availablePartitions.size();
                return (availablePartitions.get(part)).partition();
            } else {
                return Utils.toPositive(partitionNumber) % numberOfPartitions;
            }
        } else {
            return Utils.toPositive(Utils.murmur2(keyBytes)) % numberOfPartitions;
        }
    }

    private int nextPartition(String topic) {
        AtomicInteger counter = this.topicCounterMap.get(topic);
        if (null == counter) {
            counter = new AtomicInteger((new Random()).nextInt());
            AtomicInteger currentCounter = this.topicCounterMap.putIfAbsent(topic, counter);
            if (currentCounter != null) {
                counter = currentCounter;
            }
        }

        return counter.getAndIncrement();
    }

    @Override
    public void close() {
    }

}
