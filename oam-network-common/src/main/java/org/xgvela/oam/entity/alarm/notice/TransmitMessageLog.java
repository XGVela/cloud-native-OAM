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

/**
 * <p>
 * TransmitMessageLog eneity
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_transmit_message_log")
@ApiModel(value = "TransmitMessageLog", description = "TransmitMessageLog")
public class TransmitMessageLog implements Serializable {

    private static final long serialVersionUID = 4363856379724733490L;

    @ApiModelProperty(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "developerId")
    @TableField("developer_id")
    private String developerId;

    @ApiModelProperty(value = "content")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "status")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "ruleUserId")
    @TableField("rule_user_id")
    private Long ruleUserId;

    @ApiModelProperty(value = "userId")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "userName")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "sendTime")
    @TableField("send_time")
    private Date sendTime;

    @ApiModelProperty(value = "sendMethod")
    @TableField("send_method")
    private String sendMethod;

    @ApiModelProperty(value = "eventId")
    @TableField("event_id")
    private String eventId;

    @ApiModelProperty(value = "eventName")
    @TableField("event_name")
    private String eventName;

    @ApiModelProperty(value = "ruleId")
    @TableField("rule_id")
    private Long ruleId;

    @ApiModelProperty(value = "kafkaOffset")
    @TableField("kafka_offset")
    private Long kafkaOffset;

    @ApiModelProperty(value = "alarmId")
    @TableField("alarm_id")
    private String alarmId;

    public enum ExportType {
        XLSX,
        TXT
    }

    @AllArgsConstructor
    public enum statusEnum {

        WAIT("Pending sending"), SUCCESS("Sent"), FAIL("Failed to send"), CANCEL("Cancel sending");

        @Getter
        private final String value;
    }
}
