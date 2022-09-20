package org.xgvela.oam.entity.alarm.history;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * HistoryAlarm entity
 * </p>
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("oam_alarm_history")
public class HistoryAlarm extends Model<HistoryAlarm> implements Serializable {

    private static final long serialVersionUID = -403543294728058908L;

    public final static String ALARM_DATE_PARAM_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @ApiModelProperty(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "alarmNO")
    @TableField("alarm_no")
    private String alarmNO;

    @ApiModelProperty(value = "alarmId")
    @TableField("alarm_id")
    private String alarmId;

    @ApiModelProperty(value = "neId")
    @TableField("ne_id")
    private String neId;

    @ApiModelProperty(value = "neName")
    @TableField("ne_name")
    private String neName;

    @ApiModelProperty(value = "rmUid")
    @TableField("rm_uid")
    private String rmUid;

    @ApiModelProperty(value = "province")
    @TableField("province")
    private String province;

    @ApiModelProperty(value = "neType")
    @TableField("ne_type")
    private String neType;

    @ApiModelProperty(value = "alarmObjectUid")
    @TableField("alarm_object_uid")
    private String alarmObjectUid;

    @ApiModelProperty(value = "alarmObjectName")
    @TableField("alarm_object_name")
    private String alarmObjectName;

    @ApiModelProperty(value = "alarmName")
    @TableField("alarm_name")
    private String alarmName;

    @ApiModelProperty(value = "alarmType")
    @TableField("alarm_type")
    private Integer alarmType;

    @ApiModelProperty(value = "alarmLevel")
    @TableField("alarm_level")
    private Integer alarmLevel;

    @ApiModelProperty(value = "alarmStatusType")
    @TableField("alarm_status_type")
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
    @TableField("alarm_cleared_type")
    private Integer alarmClearedType;

    @ApiModelProperty(value = "alarmDeviceType")
    @TableField("alarm_device_type")
    private String alarmDeviceType;

    @ApiModelProperty(value = "alarmLocationInfo")
    @TableField("alarm_location_info")
    private String alarmLocationInfo;

    @ApiModelProperty(value = "alarmPvFlag")
    @TableField("alarm_pv_flag")
    private Integer alarmPvFlag;

    @ApiModelProperty(value = "alarmDetail")
    @TableField("alarm_detail")
    private String alarmDetail;

    @ApiModelProperty(value = "specificProblemId")
    @TableField("specific_problem_id")
    private String specificProblemId;

    @ApiModelProperty(value = "specificProblem")
    @TableField("specific_problem")
    private String specificProblem;

    @ApiModelProperty(value = "alarmObjectType")
    @TableField("alarm_object_type")
    private String alarmObjectType;

    @ApiModelProperty(value = "alarmAddInfo")
    @TableField("alarm_add_info")
    private String alarmAddInfo;

    @ApiModelProperty(value = "alarmEventTitle")
    @TableField("alarm_event_title")
    private String alarmEventTitle;

    @ApiModelProperty(value = "alarmConfirmTime")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_confirm_time",jdbcType = JdbcType.TIMESTAMP)
    private Date alarmConfirmTime;

    @ApiModelProperty(value = "ackState")
    @TableField("ack_state")
    private Integer ackState;

    @ApiModelProperty(value = "alarmConfirmUserid")
    @TableField("alarm_confirm_userid")
    private Long alarmConfirmUserid;

    @ApiModelProperty(value = "alarmConfirmUsername")
    @TableField("alarm_confirm_username")
    private String alarmConfirmUsername;

    @ApiModelProperty(value = "mergeFlag")
    @TableField("merge_flag")
    private Integer mergeFlag;

    @ApiModelProperty(value = "alarmStorageTime")
    @TableField("alarm_storage_time")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    private Date alarmStorageTime;

    @ApiModelProperty(value = "offLine")
    @TableField("off_line")
    private Integer offLine;

    @ApiModelProperty(value = "syncType")
    @TableField("sync_type")
    private Integer syncType;

    @ApiModelProperty(value = "developerId")
    @TableField("developer_id")
    private String developerId;

    @TableField("product_key")
    private String productKey;

    @TableField("group_id")
    private String groupId;

    @TableField("created_by")
    private String createdBy;

    @TableField("updated_by")
    private String updatedBy;

    @TableField("updated_at")
    private Date updatedAt;

    @TableField("domain_id")
    private Integer domainId;

    @TableField("domain_name")
    private String domainName;

    @TableField("project_id")
    private String projectId;

    @TableField("project_name")
    private String projectName;

    @ApiModelProperty(value = "source")
    @TableField("source")
    private String source;
}
