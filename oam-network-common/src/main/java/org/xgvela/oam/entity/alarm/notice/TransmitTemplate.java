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
@TableName("alarm_transmit_template")
@ApiModel(value = "TransmitTemplate", description = "Alarm forwarding rule template ")
public class TransmitTemplate implements Serializable {

    private static final long serialVersionUID = -410586061007549154L;

    @ApiModelProperty(value = "primary key ")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "developer id")
    @TableField("developer_id")
    private String developerId;

    @ApiModelProperty(value = "template name ")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "Alarm event id")
    @TableField("event_id")
    private String eventId;

    @ApiModelProperty(value = "Alarm event name ")
    @TableField("event_name")
    private String eventName;

    @ApiModelProperty(value = "template content ")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "update time ")
    @TableField("update_time")
    private Date updateTime;
}