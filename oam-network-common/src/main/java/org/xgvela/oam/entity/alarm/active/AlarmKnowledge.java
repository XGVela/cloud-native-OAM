package org.xgvela.oam.entity.alarm.active;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AlarmKnowledge entity ", description = "Alarm knowledge base table ")
@TableName(value = "alarm_knowledge")
public class AlarmKnowledge extends Model<AlarmKnowledge> {

    private static final long serialVersionUID = -7556752388876957230L;

    public final static String ALARM_DATE_PARAM_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @ApiModelProperty(value = "primary key ")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Alarm ID")
    @TableField("alarm_id")
    private String alarmId;

    @ApiModelProperty(value = "Alarm cause ID")
    @TableField("specific_problem_id")
    private String specificProblemId;

    @ApiModelProperty(value = "Cause of alarm ")
    @TableField("specific_problem")
    private String specificProblem;

    @ApiModelProperty(value = "Alarm cause analysis ")
    @TableField("specific_analyse")
    private String specificAnalyse;

    @ApiModelProperty(value = "id of the person who submitted the alarm cause analysis ")
    @TableField("specific_user_id")
    private String specificUserId;

    @ApiModelProperty(value = "Name of the person who proposed the alarm cause analysis ")
    @TableField("specific_username")
    private String specificUsername;

    @ApiModelProperty(value = "Time to raise alarm cause ")
    @TableField("specific_time")
    private Date specificTime;

    @ApiModelProperty(value = "Tenant ID")
    @TableField("developer_id")
    private String developerId;
}