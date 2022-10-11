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


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_transmit_rule_condition")
@ApiModel(value = "TransmitRuleCondition", description = "Forward Rule execution Condition entity ")
public class TransmitRuleCondition implements Serializable {

    private static final long serialVersionUID = -2675582708240368531L;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("developer_id")
    private String developerId;

    @ApiModelProperty(value = "forwarding rule id")
    @TableField("rule_id")
    private Long ruleId;

    @ApiModelProperty(value = "Forward Event table field name ")
    @TableField("field_en_name")
    private String fieldEnName;

    @ApiModelProperty(value = "operator ")
    @TableField("operator")
    private String operator;

    @ApiModelProperty(value = "Chinese name of Forward Event Table field ")
    @TableField("field_cn_name")
    private String fieldCnName;

    @ApiModelProperty(value = "Forward event Table field value ")
    @TableField("field_value")
    private String fieldValue;

    @ApiModelProperty(value = "update time ")
    @TableField("update_time")
    private Date updateTime;
}