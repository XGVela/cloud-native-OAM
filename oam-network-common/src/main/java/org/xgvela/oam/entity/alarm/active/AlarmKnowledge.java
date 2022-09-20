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

/**
 * <p>
 * AlarmKnowledge entity
 * </p>
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AlarmKnowledge entity", description = "AlarmKnowledge")
@TableName(value = "alarm_knowledge")
public class AlarmKnowledge extends Model<AlarmKnowledge> {

    private static final long serialVersionUID = -7556752388876957230L;
    
    public final static String ALARM_DATE_PARAM_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @ApiModelProperty(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "alarmId")
    @TableField("alarm_id")
    private String alarmId;

    @ApiModelProperty(value = "specificProblemId")
    @TableField("specific_problem_id")
    private String specificProblemId;

    @ApiModelProperty(value = "specificProblem")
    @TableField("specific_problem")
    private String specificProblem;

    @ApiModelProperty(value = "specificAnalyse")
    @TableField("specific_analyse")
    private String specificAnalyse;

    @ApiModelProperty(value = "specificUserId")
    @TableField("specific_user_id")
    private String specificUserId;

    @ApiModelProperty(value = "specificUsername")
    @TableField("specific_username")
    private String specificUsername;

    @ApiModelProperty(value = "specificTime")
    @TableField("specific_time")
    private Date specificTime;
    
    @ApiModelProperty(value = "developerId")
    @TableField("developer_id")
    private String developerId;
}
