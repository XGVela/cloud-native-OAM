package org.xgvela.oam.entity.alarm.active;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author maopu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlarmCountMonitorVo {

    @JsonProperty("alarmObjectName")
    private String alarmObjectName;

    @JsonProperty("groupId")
    private String groupId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("developerId")
    private String developerId;
}
