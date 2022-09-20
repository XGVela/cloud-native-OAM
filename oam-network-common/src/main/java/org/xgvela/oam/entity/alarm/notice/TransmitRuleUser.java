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
 * TransmitRuleUser entity
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_transmit_rule_user")
@ApiModel(value = "TransmitRuleUser", description = "TransmitRuleUser")
public class TransmitRuleUser implements Serializable {

    private static final long serialVersionUID = -3305730871057816134L;

    @ApiModelProperty(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "developerId")
    @TableField("developer_id")
    private String developerId;

    @ApiModelProperty(value = "userId")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "userName")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "userPhone")
    @TableField("user_phone")
    private String userPhone;

    @ApiModelProperty(value = "ruleId")
    @TableField("rule_id")
    private Long ruleId;

    @ApiModelProperty(value = "updateTime")
    @TableField("update_time")
    private Date updateTime;
}
