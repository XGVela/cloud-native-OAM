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
@ApiModel(value = "ActiveAlarmClass", description = "ActiveAlarmClass")
@TableName("alarm_active")
public class ActiveAlarmClass implements Serializable {

	private static final long serialVersionUID = 2350868664290797768L;

	@ApiModelProperty(value = "alarm_type")
	@TableField("alarm_type")
	private String alarmType;

	@ApiModelProperty(value = "count")
	@TableField(exist = false)
	private long count;
}