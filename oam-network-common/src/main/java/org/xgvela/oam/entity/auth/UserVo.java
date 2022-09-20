package org.xgvela.oam.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mepm_user_validity")
@ApiModel(value = "UserValidityData", description = "UserValidityData")
public class UserVo implements Serializable {
    private static final long serialVersionUID = -5590566016753866661L;

    @TableId(value = "user_id")
    private Long userId;

    @TableField(value = "user_validity")
    private Date userValidity;

    @TableField(value = "user_ps_validity")
    private Date userPsValidity;

    @TableField(value = "user_ps_update")
    private Date userPsUpdate;

    @TableField(value = "login_time")
    private Date loginTime;

    @TableField(value = "login_error_count")
    private int loginErrorCount;

    @TableField(value = "login_error_time")
    private Date loginErrorTime;

    @TableField(value = "unit")
    private String unit;

    @TableField(value = "time_range")
    private String timeRange;

    @ApiModelProperty(value = "accessErrorCount")
    private Integer accessErrorCount;

    @ApiModelProperty(value = "accessErrorTime")
    private Date accessErrorTime;
}
