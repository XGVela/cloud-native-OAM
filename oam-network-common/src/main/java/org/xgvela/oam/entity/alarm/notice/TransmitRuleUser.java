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
@TableName("alarm_transmit_rule_user")
@ApiModel(value = "TransmitRuleUser", description = "TransmitruleUser - Receive User entity ")
public class TransmitRuleUser implements Serializable {

    private static final long serialVersionUID = -3305730871057816134L;

    @ApiModelProperty(value = "primary key ")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "developer id")
    @TableField("developer_id")
    private String developerId;

    @ApiModelProperty(value = "recipient id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "Recipient name ")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "Recipient contact number ")
    @TableField("user_phone")
    private String userPhone;

    @ApiModelProperty(value = "Alarm forwarding rule id")
    @TableField("rule_id")
    private Long ruleId;

    @ApiModelProperty(value = "update time ")
    @TableField("update_time")
    private Date updateTime;
}