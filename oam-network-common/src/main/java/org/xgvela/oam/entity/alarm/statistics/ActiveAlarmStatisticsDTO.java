package org.xgvela.oam.entity.alarm.statistics;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * ActiveAlarmStatisticsDTO dto
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActiveAlarmStatisticsDTO implements Serializable {

    private static final long serialVersionUID = 8504020187622715450L;

    @ApiModelProperty(value = "type")
    private String type;

    @ApiModelProperty(value = "count")
    private Integer count;
}
