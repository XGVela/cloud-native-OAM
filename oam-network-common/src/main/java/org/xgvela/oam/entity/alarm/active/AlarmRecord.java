package org.xgvela.oam.entity.alarm.active;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AlarmRecord entity ", description = "AlarmRecord entity")
@TableName(autoResultMap = true, value = "alarm_record")
@ToString
public class AlarmRecord extends Model<AlarmRecord> {

    private static final long serialVersionUID = 1L;

    public final static String ALARM_DATE_PARAM_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "Alarm id")
    private String alarmId;

    @ApiModelProperty(value = "NE Id")
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
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_event_time",jdbcType = JdbcType.TIMESTAMP)
    private Date alarmEventTime;

    @ApiModelProperty(value = "Alarm clearance time ")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_cleared_time",jdbcType = JdbcType.TIMESTAMP)
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
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_confirm_time",jdbcType = JdbcType.TIMESTAMP)
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
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField("alarm_storage_time")
    private Date alarmStorageTime;

    @ApiModelProperty(value = "Whether the alarm is offline ")
    private Integer offLine;

    @ApiModelProperty(value = "Alarm number ")
    private String alarmNo;

    @ApiModelProperty(value = "source ")
    private String source;

    @ApiModelProperty(value = "developer Id")
    private String developerId;

    @ApiModelProperty(value = "product Id")
    private String productKey;

    @ApiModelProperty(value = "domain ID")
    private Integer domainId;

    @ApiModelProperty(value = "domain name ")
    private String domainName;

    @ApiModelProperty(value = "project ID")
    private String projectId;

    @ApiModelProperty(value = "project name ")
    private String projectName;

    @ApiModelProperty(value = "creator ")
    private String createdBy;

    @ApiModelProperty(value = "updater ")
    private String updatedBy;

    @ApiModelProperty(value = "update time ")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "group id")
    private String groupId;
}