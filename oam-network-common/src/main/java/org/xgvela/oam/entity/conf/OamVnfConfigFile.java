package org.xgvela.oam.entity.conf;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *  entity
 * </p>
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OamVnfConfigFile", description = "OamVnfConfigFile")
public class OamVnfConfigFile extends Model<OamVnfConfigFile> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "vnfInstanceId")
    private String vnfInstanceId;

    @ApiModelProperty(value = "vnfType")
    private String vnfType;

    @ApiModelProperty(value = "vnfName")
    private String vnfName;

    @ApiModelProperty(value = "cfVersion")
    private String cfVersion;

    @ApiModelProperty(value = "cfPath")
    private String cfPath;

    @ApiModelProperty(value = "cfName")
    private String cfName;

    @ApiModelProperty(value = "cfSize")
    private String cfSize;

    @ApiModelProperty(value = "cfUpdatetime")
    private Date cfUpdatetime;
}
