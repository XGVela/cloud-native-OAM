package org.xgvela.oam.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
* <p>
    *  实体类
    * </p>
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("oam_users")
@ApiModel(value = "OamUsers实体", description = "")
public class OamUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上层网管登录ID")
    @TableId(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "上层网管登录密码")
    @TableField("user_pass")
    private String userPass;

    @ApiModelProperty(value = "上层网管系统ID")
    @TableField("system_id")
    private String systemId;

}
