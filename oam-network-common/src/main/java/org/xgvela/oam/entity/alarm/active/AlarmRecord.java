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

/**
 * <p>
 *  entity
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AlarmRecord entity", description = "")
@TableName(autoResultMap = true, value = "alarm_record")
@ToString
public class AlarmRecord extends Model<AlarmRecord> {

    private static final long serialVersionUID = 1L;

    public final static String ALARM_DATE_PARAM_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @ApiModelProperty(value = "id")
    private Long id;

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
    @TableField(value = "alarm_event_time",jdbcType = JdbcType.TIMESTAMP)
    private Date alarmEventTime;

    @ApiModelProperty(value = "alarmClearedTime")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_cleared_time",jdbcType = JdbcType.TIMESTAMP)
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
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_confirm_time",jdbcType = JdbcType.TIMESTAMP)
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
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField("alarm_storage_time")
    private Date alarmStorageTime;

    @ApiModelProperty(value = "offLine")
    private Integer offLine;

    @ApiModelProperty(value = "alarmNo")
    private String alarmNo;

    @ApiModelProperty(value = "source")
    private String source;

    @ApiModelProperty(value = "developerId")
    private String developerId;

    @ApiModelProperty(value = "productKey")
    private String productKey;

    @ApiModelProperty(value = "domainId")
    private Integer domainId;

    @ApiModelProperty(value = "domainName")
    private String domainName;

    @ApiModelProperty(value = "projectId")
    private String projectId;

    @ApiModelProperty(value = "projectName")
    private String projectName;

    @ApiModelProperty(value = "createdBy")
    private String createdBy;

    @ApiModelProperty(value = "updatedBy")
    private String updatedBy;

    @ApiModelProperty(value = "updatedAt")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "groupId")
    private String groupId;

}
