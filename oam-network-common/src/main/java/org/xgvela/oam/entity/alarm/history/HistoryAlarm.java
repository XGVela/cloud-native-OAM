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
 * HistoryAlarm 历史告警实体类
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

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("alarm_no")
    private String alarmNO;

    @TableField("alarm_id")
    private String alarmId;

    @TableField("ne_id")
    private String neId;

    @TableField("ne_name")
    private String neName;

    @TableField("rm_uid")
    private String rmUid;

    @TableField("province")
    private String province;

    @TableField("ne_type")
    private String neType;

    @TableField("alarm_object_uid")
    private String alarmObjectUid;

    @TableField("alarm_object_name")
    private String alarmObjectName;

    @TableField("alarm_name")
    private String alarmName;

    @TableField("alarm_type")
    private Integer alarmType;

    @TableField("alarm_level")
    private Integer alarmLevel;

    @TableField("alarm_status_type")
    private Integer alarmStatusType;

    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_event_time",jdbcType = JdbcType.TIMESTAMP)
    private Date alarmEventTime;

    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_cleared_time",jdbcType = JdbcType.TIMESTAMP)
    private Date alarmClearedTime;

    @TableField("alarm_cleared_type")
    private Integer alarmClearedType;

    @TableField("alarm_device_type")
    private String alarmDeviceType;

    @TableField("alarm_location_info")
    private String alarmLocationInfo;

    @TableField("alarm_pv_flag")
    private Integer alarmPvFlag;

    @TableField("alarm_detail")
    private String alarmDetail;

    @TableField("specific_problem_id")
    private String specificProblemId;

    @TableField("specific_problem")
    private String specificProblem;

    @TableField("alarm_object_type")
    private String alarmObjectType;

    @TableField("alarm_add_info")
    private String alarmAddInfo;

    @TableField("alarm_event_title")
    private String alarmEventTitle;

    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_confirm_time",jdbcType = JdbcType.TIMESTAMP)
    private Date alarmConfirmTime;

    @TableField("ack_state")
    private Integer ackState;

    @TableField("alarm_confirm_userid")
    private Long alarmConfirmUserid;

    @TableField("alarm_confirm_username")
    private String alarmConfirmUsername;

    @TableField("merge_flag")
    private Integer mergeFlag;

    @TableField("alarm_storage_time")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    private Date alarmStorageTime;

    @TableField("off_line")
    private Integer offLine;

    @TableField("sync_type")
    private Integer syncType;

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

    @TableField("source")
    private String source;
}
