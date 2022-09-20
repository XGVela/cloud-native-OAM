package org.xgvela.oam.entity.subscribe;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  Entity
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OamSubscribe entity")
public class OamSubscribe extends Model<OamSubscribe> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "nfid")
    @TableField(updateStrategy = FieldStrategy.IGNORED, insertStrategy = FieldStrategy.IGNORED)
    private String neId;

    @ApiModelProperty(value = "type:register,alarm,perf")
    private String dataType;

    @ApiModelProperty(value = "callback URL")
    private String callbackUrl;

    @ApiModelProperty(value = "omc ID")
    private String systemId;

}
