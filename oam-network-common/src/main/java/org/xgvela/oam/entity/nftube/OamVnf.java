package org.xgvela.oam.entity.nftube;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *  Entity
 * </p>
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OamVnf entity", description = "")
public class OamVnf extends Model<OamVnf> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "nfid")
    @TableId
    private String neId;

    @ApiModelProperty(value = "nftype")
    private String neType;

    @ApiModelProperty(value = "nfname")
    private String vnfName;

    @ApiModelProperty(value = "ip")
    private String vnfManageIp;

    @ApiModelProperty(value = "port")
    private String vnfManagePort;

    @ApiModelProperty(value = "vnfSignalPort")
    private String vnfSignalPort;

    @ApiModelProperty(value = "vnfStatus")
    @TableField(updateStrategy = FieldStrategy.IGNORED, insertStrategy = FieldStrategy.IGNORED)
    private String vnfStatus;

    @ApiModelProperty(value = "vnfManageStatus")
    @TableField(updateStrategy = FieldStrategy.IGNORED, insertStrategy = FieldStrategy.IGNORED)
    private String vnfManageStatus;

    @ApiModelProperty(value = "heartBeatTime")
    private Date heartBeatTime;

    @ApiModelProperty(value = "systemId")
    @TableField(updateStrategy = FieldStrategy.IGNORED, insertStrategy = FieldStrategy.IGNORED)
    private String systemId;

    @ApiModelProperty(value = "confStatus")
    private String confStatus;

    @AllArgsConstructor
    @ToString
    public enum confStatusType {
        DOWLOADED("1", "The configuration has been downloaded"), UNDOWNLOADED("0", "The configuration was not downloaded");
        @Getter
        private final String id;
        @Getter
        private final String name;
    }
}
