package org.xgvela.oam.entity.alarm.analysis;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmStructure {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String alarmNO;

    private String alarmId;

    @ApiModelProperty(value = "NE ID")
    private String neId;

    @ApiModelProperty(value = "NE name ")
    private String neName;

    @ApiModelProperty(value = "Alarm name ")
    private String alarmName;

    @ApiModelProperty(value = "Alarm type ")
    private Integer alarmType;

    @ApiModelProperty(value = "Alarm severity ")
    private Integer alarmLevel;

    @ApiModelProperty(value = "Alarm status type ")
    private Integer alarmStatusType;

    @ApiModelProperty(value = "alarm generation time ")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date alarmEventTime;

    @ApiModelProperty(value = "Alarm clearance time ")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date alarmClearedTime;

    @ApiModelProperty(value = "Alarm clearance type ")
    private Integer alarmClearedType;

    @ApiModelProperty(value = "Alarm object ")
    private String alarmObject;

    @ApiModelProperty(value = "Alarm location information ")
    private String alarmLocationInfo;

    @ApiModelProperty(value = "NE virtualization identifier ")
    private Integer alarmPvFlag;

    @ApiModelProperty(value = "Alarm details ")
    private String alarmDetail;

    @ApiModelProperty(value = "Alarm cause ID")
    private String specificProblemId;

    @ApiModelProperty(value = "Cause of alarm ")
    private String specificProblem;

    @ApiModelProperty(value = "Alarm NE device type ")
    private String neType;

    @ApiModelProperty(value = "UID of alarm location object ")
    private String alarmObjectUid;

    @ApiModelProperty(value = "Alarm location object name ")
    private String alarmObjectName;

    @ApiModelProperty(value = "Alarm location object resource type ")
    private String alarmObjectType;

    @ApiModelProperty(value = "Auxiliary Alarm information ")
    private String alarmAddInfo;

    @ApiModelProperty(value = "Alarm event title ")
    private String alarmEventTitle;

    @ApiModelProperty(value = "NE service province ")
    private String province;

    @ApiModelProperty(value = "alarm acknowledgement time ")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date alarmConfirmTime;

    @ApiModelProperty(value = "Alarm acknowledgement status ")
    private Integer ackState;

    @ApiModelProperty(value = "Alarm Acknowledgement user ID")
    private String alarmConfirmUserid;

    @ApiModelProperty(value = "Alarm acknowledgment user name ")
    private String alarmConfirmUsername;

    @ApiModelProperty(value = "Alarm device type ")
    private String alarmDeviceType;

    @ApiModelProperty(value = "Alarm NE UID")
    private String rmUid;

    @ApiModelProperty(value = "Alarm NE name ")
    private String neUname;

    @ApiModelProperty(value = "Whether alarms have been merged and cleared ")
    private Integer mergeFlag;

    @ApiModelProperty(value = "Type of synchronized alarm ")
    private Integer syncType;

    @ApiModelProperty(value = "alarm entry time ")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date alarmStorageTime;

    @ApiModelProperty(value = "Alarm heartbeat online or offline ")
    private Integer offLine;

    @ApiModelProperty(value = "source ")
    private String source;

}