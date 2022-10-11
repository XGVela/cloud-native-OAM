package org.xgvela.oam.entity.alarm.notice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_transmit_event")
@ApiModel(value = "TransmitEvent", description = "TransmitEvent")
public class TransmitEvent extends Model<TransmitEvent> implements Serializable {

    private static final long serialVersionUID = 7179441478046272805L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField("name")
    private String name;

    @TableField("table_name")
    private String tableName;

    @TableField("update_time")
    private Date updateTime;

    @TableField(exist = false)
    private List<TransmitEventField> eventFieldList;

    @AllArgsConstructor
    public enum eventTypeEnum{

        ALARM("1", "ALARM"),
        ALARM_CLEARED("2", "ALARM_CLEARED");

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
        private Long kafkaOffSet;
    }
}
