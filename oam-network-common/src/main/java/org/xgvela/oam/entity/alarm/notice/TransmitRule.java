package org.xgvela.oam.entity.alarm.notice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * TransmitRule entity
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_transmit_rule")
@ApiModel(value = "TransmitRule", description = "TransmitRule")
public class TransmitRule implements Serializable {

    private static final long serialVersionUID = -5735666830091560860L;

    @ApiModelProperty(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "developerId")
    @TableField("developer_id")
    private String developerId;

    @ApiModelProperty(value = "name")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "delayTime")
    @TableField("delay_time")
    private Integer delayTime;

    @ApiModelProperty(value = "status")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "eventId")
    @TableField("event_id")
    private String eventId;

    @ApiModelProperty(value = "eventName")
    @TableField("event_name")
    private String eventName;

    @ApiModelProperty(value = "templateId")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("template_id")
    private Long templateId;

    @ApiModelProperty(value = "templateName")
    @TableField("template_name")
    private String templateName;

    @ApiModelProperty(value = "sendMethod")
    @TableField("send_method")
    private String sendMethod;

    @ApiModelProperty(value = "updateTime")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "userList")
    @TableField(exist = false)
    private List<TransmitRuleUser> userList;

    @ApiModelProperty(value = "conditionList")
    @TableField(exist = false)
    private List<TransmitRuleCondition> conditionList;

    @AllArgsConstructor
    public enum statusEnum {
        INACTIVE, ACTIVE;
    }

    @AllArgsConstructor
    public enum sendMethodEnum {
        SMS("sms"), QYWEIXIN("Wechat");

        @Getter
        private final String value;
    }
}
