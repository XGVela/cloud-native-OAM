package org.xgvela.oam.entity.alarm.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.xgvela.oam.entity.serialize.DefaultInstantDeserializer;
import org.xgvela.oam.entity.serialize.DefaultInstantSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.influxdb.annotation.Column;

import java.time.Instant;

/**
 * @author maopu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlarmCountMonitor {

    @JsonDeserialize(using = DefaultInstantDeserializer.class)
    @JsonSerialize(using = DefaultInstantSerializer.class)
    @Column(name = "time")
    private Instant time;

    @JsonProperty("alarmObjectName")
    @Column(name = "alarmObjectName")
    private String alarmObjectName;

    @JsonProperty("groupId")
    @Column(name = "groupId")
    private String groupId;

    @JsonProperty("monitorType")
    @Column(name = "monitorType")
    private String monitorType;

    @JsonProperty("msgCount")
    @Column(name = "msgCount")
    private Integer msgCount;

    @JsonProperty("developerId")
    @Column(name = "developerId")
    private String developerId;

}
