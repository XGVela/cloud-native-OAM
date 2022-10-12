package org.xgvela.oam.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("oam_users")
@ApiModel(value = "OamUsers entity ", description = "")
public class OamUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "upper-layer NMS login ID")
    @TableId(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "upper-layer NMS login password ")
    @TableField("user_pass")
    private String userPass;

    @ApiModelProperty(value = "upper-layer OSS ID")
    @TableField("system_id")
    private String systemId;

}