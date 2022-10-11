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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_transmit_message_log")
@ApiModel(value = "TransmitMessageLog", description = "TransmitMessageLog")
public class TransmitMessageLog implements Serializable {

    private static final long serialVersionUID = 4363856379724733490L;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("developer_id")
    private String developerId;

    @ApiModelProperty(value = "content")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "status")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "rule_user_id")
    @TableField("rule_user_id")
    private Long ruleUserId;

    @ApiModelProperty(value = "user_id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "user_name")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "send_time")
    @TableField("send_time")
    private Date sendTime;

    @ApiModelProperty(value = "send_method")
    @TableField("send_method")
    private String sendMethod;

    @ApiModelProperty(value = "event_id")
    @TableField("event_id")
    private String eventId;

    @ApiModelProperty(value = "event_name")
    @TableField("event_name")
    private String eventName;

    @ApiModelProperty(value = "rule_id")
    @TableField("rule_id")
    private Long ruleId;

    @ApiModelProperty(value = "kafka_offset")
    @TableField("kafka_offset")
    private Long kafkaOffset;

    @ApiModelProperty(value = "alarm_id")
    @TableField("alarm_id")
    private String alarmId;

    public enum ExportType {
        XLSX,
        TXT
    }

    @AllArgsConstructor
    public enum statusEnum {
        WAIT("WAIT"), SUCCESS("SUCCESS"), FAIL("FAIL"), CANCEL("CANCEL");
        @Getter
        private final String value;
    }
}
