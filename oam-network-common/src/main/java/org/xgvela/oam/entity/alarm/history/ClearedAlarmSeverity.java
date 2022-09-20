package org.xgvela.oam.entity.alarm.history;

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
@ApiModel(value = "ClearedAlarmSeverity", description = "ClearedAlarmSeverity")
@TableName("alarm_history")
public class ClearedAlarmSeverity implements Serializable {

	private static final long serialVersionUID = -4874495954295611867L;

	/**
	 * alarm_level
	 */
	@ApiModelProperty(value = "alarm_level")
	@TableField("alarm_level")
	private String alarmLevel;

	@ApiModelProperty(value = "count")
	@TableField(exist = false)
	private long count;
}