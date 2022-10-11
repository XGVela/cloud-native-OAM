package org.xgvela.oam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AlarmVo implements Serializable {

    private static final long serialVersionUID = 1L;

    public final static String ALARM_DATE_PARAM_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @ApiModelProperty(value = "major key")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "The alarm number")
    private String alarmNO;

    @ApiModelProperty(value = "Alarm unique Identifier")
    private String alarmId;

    @ApiModelProperty(value = "NE ID")
    private String neId;

    @ApiModelProperty(value = "NE Name")
    private String neName;

    @ApiModelProperty(value = "Alarm name")
    private String alarmName;

    @ApiModelProperty(value = "alert type")
    private Integer alarmType;


    @ApiModelProperty(value = "alarm level")
    private Integer alarmLevel;

    @ApiModelProperty(value = "Alarm Status Type")
    private Integer alarmStatusType;


    @ApiModelProperty(value = "Alarm generation Time")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    private Date alarmEventTime;


    @ApiModelProperty(value = "Alarm Clearing Time")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    private Date alarmClearedTime;


    @ApiModelProperty(value = "Alarm Clearance Type")
    private Integer alarmClearedType;


    @ApiModelProperty(value = "Alert Objects")
    private String alarmObject;


    @ApiModelProperty(value = "Alarm Location Information")
    private String alarmLocationInfo;


    @ApiModelProperty(value = "Virtualization identifier of the NE")
    private Integer alarmPvFlag;


    @ApiModelProperty(value = "Alarm Details")
    @TableField("alarm_detail")
    private String alarmDetail;


    @ApiModelProperty(value = "ID of the cause of an alarm problem")
    private String specificProblemId;


    @ApiModelProperty(value = "Alarm Fault Cause")
    private String specificProblem;


    @ApiModelProperty(value = "Device type of the NE for which the alarm is generated")
    private String neType;


    @ApiModelProperty(value = "UID of the alarm location object")
    @TableField("alarm_object_uid")
    private String alarmObjectUid;

    @ApiModelProperty(value = "Name of the object for which the alarm is located")
    @TableField("alarm_object_name")
    private String alarmObjectName;


    @ApiModelProperty(value = "Resource type of the object for which the alarm is located")
    private String alarmObjectType;


    @ApiModelProperty(value = "Auxiliary Alarm Information")
    private String alarmAddInfo;


    @ApiModelProperty(value = "Alarm Event Title")
    private String alarmEventTitle;


    @ApiModelProperty(value = "The NE serves the province")
    @TableField("province")
    private String province;


    @ApiModelProperty(value = "Alarm confirmation Time")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    private Date alarmConfirmTime;


    @ApiModelProperty(value = "Alarm acknowledgement Status")
    private Integer ackState;


    @ApiModelProperty(value = "ID of the user who acknowledges the alarm")
    private String alarmConfirmUserid;


    @ApiModelProperty(value = "Name of the alarm confirmation user")
    private String alarmConfirmUsername;

    @ApiModelProperty(value = "Alarm Device Type")
    @TableField("alarm_device_type")
    private String alarmDeviceType;

    @ApiModelProperty(value = "Alarm Ne UID")
    private String rmUid;

    @ApiModelProperty(value = "Name of the alarm NE")
    @TableField("ne_uname")
    private String neUname;

    @ApiModelProperty(value = "Check whether alarms have been merged and cleared")
    private Integer mergeFlag;

    @ApiModelProperty(value = "Synchronization alarm type")
    private Integer syncType;

    @ApiModelProperty(value = "Time when the alarm is stored")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    private Date alarmStorageTime;

    @ApiModelProperty(value = "The alarm heartbeat is online or offline")
    private Integer offLine;

    @ApiModelProperty(value = "source")
    private String source;
}
