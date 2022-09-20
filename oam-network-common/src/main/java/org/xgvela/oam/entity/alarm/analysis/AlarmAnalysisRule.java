package org.xgvela.oam.entity.alarm.analysis;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.xgvela.oam.entity.alarm.analysis.convert.ActiveFilterTypeHandler;
import org.xgvela.oam.entity.alarm.analysis.convert.LogFilterTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("AlarmAnalysisRule")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "alarm_analysis_rule",autoResultMap = true)
public class AlarmAnalysisRule extends Model<AlarmAnalysisRule> implements Serializable {

    private static final long serialVersionUID = -4653275173146245389L;

    @ApiModelProperty(value = "id",hidden = true)
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "rule_name")
    private String ruleName;

    @TableField(value = "time_window")
    private String timeWindow;

    @TableField(value = "active_filters", typeHandler = ActiveFilterTypeHandler.class)
    private List<ActiveFilter> activeFilters;

    @TableField(value = "active_filter_relation")
    private String activeFilterRelation;

    @TableField(value = "log_filters", typeHandler = LogFilterTypeHandler.class)
    private List<LogFilter> logFilters;

    @TableField(value = "log_filter_relation")
    private String logFilterRelation;

    @TableField(value = "response_field")
    private String responseField;

    @TableField(value = "status")
    private Boolean status;

    @TableField(value = "description")
    private String description;

    @ApiModelProperty("createTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("updateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("update_time")
    private Date updateTime;
}
