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


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_transmit_rule")
@ApiModel(value = "TransmitRule", description = "Transmitrule entity ")
public class TransmitRule implements Serializable {

    private static final long serialVersionUID = -5735666830091560860L;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("developer_id")
    private String developerId;

    @TableField("name")
    private String name;

    @ApiModelProperty(value = "Message latency (minutes) ")
    @TableField("delay_time")
    private Integer delayTime;

    @ApiModelProperty(value = "status ")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "Alarm event id")
    @TableField("event_id")
    private String eventId;

    @ApiModelProperty(value = "Alarm event name ")
    @TableField("event_name")
    private String eventName;

    @ApiModelProperty(value = "Alarm forwarding template id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("template_id")
    private Long templateId;

    @ApiModelProperty(value = "Alarm forwarding template name ")
    @TableField("template_name")
    private String templateName;

    @ApiModelProperty(value = "send ")
    @TableField("send_method")
    private String sendMethod;

    @ApiModelProperty(value = "update time ")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "recipient list")
    @TableField(exist = false)
    private List<TransmitRuleUser> userList;

    @ApiModelProperty(value = "filter criteria list")
    @TableField(exist = false)
    private List<TransmitRuleCondition> conditionList;

    @AllArgsConstructor
    public enum statusEnum {
        INACTIVE, ACTIVE;
    }

    @AllArgsConstructor
    public enum sendMethodEnum {
        SMS(" short message "), QYWEIXIN(" Enterprise wechat ");
        @Getter
        private final String value;
    }
}