package org.xgvela.oam.entity.alarm.active;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ActiveAlarm entity ", description = "ActiveAlarm Application Instance ")
@TableName(autoResultMap = true, value = "oam_alarm_active")
@ToString
public class ActiveAlarm extends Model<ActiveAlarm> {
	
	private static final long serialVersionUID = 1L;
	
	public final static String ALARM_DATE_PARAM_PATTERN = "yyyy-MM-dd HH:mm:ss";

	@ApiModelProperty(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "alarm_no")
    @TableField("alarm_no")
    private String alarmNO;

    @ApiModelProperty(value = "alarm_id")
    @TableField("alarm_id")
    private String alarmId;
    
    @ApiModelProperty(value = "ne_id")
    @TableField("ne_id")
    private String neId;
    
    @ApiModelProperty(value = "ne_name")
    @TableField("ne_name")
    private String neName;
    
    @ApiModelProperty(value = "alarm_name")
    @TableField("alarm_name")
    private String alarmName;
    
    @ApiModelProperty(value = "alarm_type")
    @TableField("alarm_type")
    private Integer alarmType;
    

    @ApiModelProperty(value = "alarm_levels")
    @TableField("alarm_level")
    private Integer alarmLevel;
    
    @ApiModelProperty(value = "alarm_status_type")
    @TableField("alarm_status_type")
    private Integer alarmStatusType;
    
    @ApiModelProperty(value = "alarm_event_time")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_event_time",jdbcType = JdbcType.TIMESTAMP)
    private Date alarmEventTime;

    @ApiModelProperty(value = "alarm_cleared_time")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_cleared_time",jdbcType = JdbcType.TIMESTAMP)
    private Date alarmClearedTime;
    
    @ApiModelProperty(value = "alarm_cleared_type")
    @TableField("alarm_cleared_type")
    private Integer alarmClearedType;

    @ApiModelProperty(value = "alarm_object")
    @TableField("alarm_object")
    private String alarmObject;

    @ApiModelProperty(value = "alarm_location_info")
    @TableField("alarm_location_info")
    private String alarmLocationInfo;

    @ApiModelProperty(value = "alarm_pv_flag")
    @TableField("alarm_pv_flag")
    private Integer alarmPvFlag;

    @ApiModelProperty(value = "alarm_detail")
    @TableField("alarm_detail")
    private String alarmDetail;

    @ApiModelProperty(value = "specific_problem_id")
    @TableField("specific_problem_id")
    private String specificProblemId;

    @ApiModelProperty(value = "specific_problem")
    @TableField("specific_problem")
    private String specificProblem;

    @ApiModelProperty(value = "ne_type")
    @TableField("ne_type")
    private String neType;

    @ApiModelProperty(value = "alarm_object_uid")
    @TableField("alarm_object_uid")
    private String alarmObjectUid;

    @ApiModelProperty(value = "alarm_object_name")
    @TableField("alarm_object_name")
    private String alarmObjectName;

    @ApiModelProperty(value = "alarm_object_type")
    @TableField("alarm_object_type")
    private String alarmObjectType;

    @ApiModelProperty(value = "alarm_add_info")
    @TableField("alarm_add_info")
    private String alarmAddInfo;
    
    @ApiModelProperty(value = "alarm_event_title")
    @TableField("alarm_event_title")
    private String alarmEventTitle;
    
    @ApiModelProperty(value = "province")
    @TableField("province")
    private String province;

    @ApiModelProperty(value = "alarm_confirm_timex")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField(value = "alarm_confirm_time",jdbcType = JdbcType.TIMESTAMP)
    private Date alarmConfirmTime;
    
    @ApiModelProperty(value = "ack_state")
    @TableField("ack_state")
    private Integer ackState;

    @ApiModelProperty(value = "alarm_confirm_userid")
    @TableField("alarm_confirm_userid")
    private String alarmConfirmUserid;

    @ApiModelProperty(value = "告警确认用户名称")
    @TableField("alarm_confirm_username")
    private String alarmConfirmUsername;

    @ApiModelProperty(value = "alarm_device_type")
    @TableField("alarm_device_type")
    private String alarmDeviceType;

    @ApiModelProperty(value = "rm_uid")
    @TableField("rm_uid")
    private String rmUid;

    @ApiModelProperty(value = "ne_uname")
    @TableField("ne_uname")
    private String neUname;

    @ApiModelProperty(value = "merge_flag")
    @TableField("merge_flag")
    private Integer mergeFlag;
    
    @ApiModelProperty(value = "sync_type")
    @TableField("sync_type")
    private Integer syncType;
    
    @ApiModelProperty(value = "alarm_storage_time")
    @JsonFormat(pattern = ALARM_DATE_PARAM_PATTERN, timezone = "GMT+8")
    @TableField("alarm_storage_time")
    private Date alarmStorageTime;
    
    @ApiModelProperty(value = "off_line")
    @TableField("off_line")
    private Integer offLine;

    @ApiModelProperty(value = "source")
    @TableField("source")
    private String source;

    @ApiModelProperty(value = "developer_id")
    @TableField("developer_id")
    private String developerId;

    @TableField(exist = false)
    private Long kafkaOffSet;

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
}
