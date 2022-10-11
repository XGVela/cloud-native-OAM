package org.xgvela.oam.entity.alarm.notice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_transmit_event_field")
@ApiModel(value = "TransmitEventField", description = "TransmitEventField")
public class TransmitEventField implements Serializable {

    private static final long serialVersionUID = 2894431192244707292L;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("event_id")
    private String eventId;

    @TableField("en_name")
    private String enName;

    @TableField("cn_name")
    private String cnName;

    @AllArgsConstructor
    public enum eventTypeEnum{

        ALARM("994f9d52ceab9f7444ce04e12301ab10","ALARM");

        @Getter
        private final String id;

        @Getter
        private final String name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventVO {
        private String eventId;
        private ActiveAlarm content;
    }
}
