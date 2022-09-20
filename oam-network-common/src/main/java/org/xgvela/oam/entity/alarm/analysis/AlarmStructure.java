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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date alarmEventTime;

    @ApiModelProperty(value = "alarmClearedTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
    private String alarmDetail;

    @ApiModelProperty(value = "specificProblemId")
    private String specificProblemId;

    @ApiModelProperty(value = "specificProblem")
    private String specificProblem;

    @ApiModelProperty(value = "neType")
    private String neType;

    @ApiModelProperty(value = "alarmObjectUid")
    private String alarmObjectUid;

    @ApiModelProperty(value = "alarmObjectName")
    private String alarmObjectName;

    @ApiModelProperty(value = "alarmObjectType")
    private String alarmObjectType;

    @ApiModelProperty(value = "alarmAddInfo")
    private String alarmAddInfo;

    @ApiModelProperty(value = "alarmEventTitle")
    private String alarmEventTitle;

    @ApiModelProperty(value = "province")
    private String province;

    @ApiModelProperty(value = "alarmConfirmTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date alarmConfirmTime;

    @ApiModelProperty(value = "ackState")
    private Integer ackState;

    @ApiModelProperty(value = "alarmConfirmUserid")
    private String alarmConfirmUserid;

    @ApiModelProperty(value = "alarmConfirmUsername")
    private String alarmConfirmUsername;

    @ApiModelProperty(value = "alarmDeviceType")
    private String alarmDeviceType;

    @ApiModelProperty(value = "rmUid")
    private String rmUid;

    @ApiModelProperty(value = "neUname")
    private String neUname;

    @ApiModelProperty(value = "mergeFlag")
    private Integer mergeFlag;

    @ApiModelProperty(value = "syncType")
    private Integer syncType;

    @ApiModelProperty(value = "alarmStorageTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date alarmStorageTime;

    @ApiModelProperty(value = "offLine")
    private Integer offLine;

    @ApiModelProperty(value = "source")
    private String source;

}
