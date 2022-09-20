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

    @ApiModelProperty(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "alarmNO")
    private String alarmNO;

    @ApiModelProperty(value = "alarmId")
    private String alarmId;

    @ApiModelProperty(value = "neId")
    private String neId;

    @ApiModelProperty(value = "neName")
    private String neName;

    @ApiModelProperty(value = "alarmName")
    private String alarmName;

    @ApiModelProperty(value = "alarmType")
    private Integer alarmType;


    @ApiModelProperty(value = "alarmLevel")
    private Integer alarmLevel;

    @ApiModelProperty(value = "alarmStatusType")
    private Integer alarmStatusType;


    @ApiModelProperty(value = "alarmEventTime")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    private Date alarmEventTime;


    @ApiModelProperty(value = "alarmClearedTime")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    private Date alarmClearedTime;


    @ApiModelProperty(value = "alarmClearedType")
    private Integer alarmClearedType;


    @ApiModelProperty(value = "alarmObject")
    private String alarmObject;


    @ApiModelProperty(value = "alarmLocationInfo")
    private String alarmLocationInfo;


    @ApiModelProperty(value = "alarmPvFlag")
    private Integer alarmPvFlag;


    @ApiModelProperty(value = "alarmDetail")
    @TableField("alarm_detail")
    private String alarmDetail;


    @ApiModelProperty(value = "specificProblemId")
    private String specificProblemId;


    @ApiModelProperty(value = "specificProblem")
    private String specificProblem;


    @ApiModelProperty(value = "neType")
    private String neType;


    @ApiModelProperty(value = "alarmObjectUid")
    @TableField("alarm_object_uid")
    private String alarmObjectUid;

    @ApiModelProperty(value = "alarmObjectName")
    @TableField("alarm_object_name")
    private String alarmObjectName;


    @ApiModelProperty(value = "alarmObjectType")
    private String alarmObjectType;


    @ApiModelProperty(value = "alarmAddInfo")
    private String alarmAddInfo;


    @ApiModelProperty(value = "alarmEventTitle")
    private String alarmEventTitle;


    @ApiModelProperty(value = "province")
    @TableField("province")
    private String province;


    @ApiModelProperty(value = "alarmConfirmTime")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    private Date alarmConfirmTime;


    @ApiModelProperty(value = "ackState")
    private Integer ackState;


    @ApiModelProperty(value = "alarmConfirmUserid")
    private String alarmConfirmUserid;


    @ApiModelProperty(value = "alarmConfirmUsername")
    private String alarmConfirmUsername;

    @ApiModelProperty(value = "alarmDeviceType")
    @TableField("alarm_device_type")
    private String alarmDeviceType;

    @ApiModelProperty(value = "rmUid")
    private String rmUid;

    @ApiModelProperty(value = "neUname")
    @TableField("ne_uname")
    private String neUname;

    @ApiModelProperty(value = "mergeFlag")
    private Integer mergeFlag;

    @ApiModelProperty(value = "syncType")
    private Integer syncType;

    @ApiModelProperty(value = "alarmStorageTime")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    private Date alarmStorageTime;

    @ApiModelProperty(value = "offLine")
    private Integer offLine;

    @ApiModelProperty(value = "source")
    private String source;
}
