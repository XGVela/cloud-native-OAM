package org.xgvela.oam.entity.alarm.notice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * TransmitRuleCondition entity
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_transmit_rule_condition")
@ApiModel(value = "TransmitRuleCondition", description = "TransmitRuleCondition")
public class TransmitRuleCondition implements Serializable {

    private static final long serialVersionUID = -2675582708240368531L;

    @ApiModelProperty(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "developerId")
    @TableField("developer_id")
    private String developerId;

    @ApiModelProperty(value = "ruleId")
    @TableField("rule_id")
    private Long ruleId;

    @ApiModelProperty(value = "fieldEnName")
    @TableField("field_en_name")
    private String fieldEnName;

    @ApiModelProperty(value = "operator")
    @TableField("operator")
    private String operator;

    @ApiModelProperty(value = "fieldCnName")
    @TableField("field_cn_name")
    private String fieldCnName;

    @ApiModelProperty(value = "fieldValue")
    @TableField("field_value")
    private String fieldValue;

    @ApiModelProperty(value = "updateTime")
    @TableField("update_time")
    private Date updateTime;
}
