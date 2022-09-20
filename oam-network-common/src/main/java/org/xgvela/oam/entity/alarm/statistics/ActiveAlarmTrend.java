package org.xgvela.oam.entity.alarm.statistics;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * ActiveAlarmTrend entity
 * </p>
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_active")
public class ActiveAlarmTrend implements Serializable {

    private static final long serialVersionUID = 2687146427792416351L;

    @ApiModelProperty(value = "alarm date")
    @TableField(exist = false)
    private String alarmTime;
    @ApiModelProperty(value = "count")
    @TableField(exist = false)
    private long count;
}
