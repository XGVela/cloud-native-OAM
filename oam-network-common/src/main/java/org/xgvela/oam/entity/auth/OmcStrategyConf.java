package org.xgvela.oam.entity.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * OmcStrategyConf
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("omc_strategy_conf")
@ApiModel(value = "OmcStrategyConf entity", description = "OmcStrategyConf")
public class OmcStrategyConf implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "type")
    private String type;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "value")
    private String value;

    @ApiModelProperty(value = "remark")
    private String remark;

    @ApiModelProperty(value = "flag")
    private Integer flag;

}
