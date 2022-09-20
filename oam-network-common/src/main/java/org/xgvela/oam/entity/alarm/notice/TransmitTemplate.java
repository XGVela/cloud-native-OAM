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
 * TransmitTemplate entity
 * </p>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("alarm_transmit_template")
@ApiModel(value = "TransmitTemplate", description = "TransmitTemplate")
public class TransmitTemplate implements Serializable {

    private static final long serialVersionUID = -410586061007549154L;

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

    @ApiModelProperty(value = "eventId")
    @TableField("event_id")
    private String eventId;

    @ApiModelProperty(value = "eventName")
    @TableField("event_name")
    private String eventName;

    @ApiModelProperty(value = "content")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "updateTime")
    @TableField("update_time")
    private Date updateTime;

}
