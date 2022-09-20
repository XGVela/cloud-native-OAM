package org.xgvela.oam.entity.alarm.active;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ActiveAlarmSeverity", description = "ActiveAlarmSeverity")
@TableName("alarm_active")
public class ActiveAlarmSeverity implements Serializable {

	private static final long serialVersionUID = 2350868664290797768L;

	@ApiModelProperty(value = "alarmLevel")
	@TableField("alarm_level")
	private String alarmLevel;

	@ApiModelProperty(value = "count")
	@TableField(exist = false)
	private long count;
}
