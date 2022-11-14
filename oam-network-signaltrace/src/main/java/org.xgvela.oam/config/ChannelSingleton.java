
package org.xgvela.oam.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
@Slf4j
public class ChannelSingleton {

    private static final String Separator = "-";

    private static Map<String, ManagedChannel> managedChannelMap = new HashMap<>();

    public ManagedChannel getManagedChannel(String host, int port){
        String key = String.format("%s%s%d", host, Separator, port);
        if (managedChannelMap.get(key) == null) {
            managedChannelMap.put(key, getContextChannel(host, port));
        }
        return managedChannelMap.get(key);
    }

    private ManagedChannel getContextChannel(String host, int port){
        return ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }
}
