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

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OamVnf entity ", description = "")
@NoArgsConstructor
@AllArgsConstructor
public class OamVnf extends Model<OamVnf> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "NE instance id")
    @TableId
    private String neId;

    @ApiModelProperty(value = "NE type ")
    private String neType;

    @ApiModelProperty(value = "NE name ")
    private String vnfName;

    @ApiModelProperty(value = "admin ip")
    private String vnfManageIp;

    @ApiModelProperty(value = "admin port ")
    private String vnfManagePort;

    @ApiModelProperty(value = "Signaling trace port ")
    private String vnfSignalPort;

    @ApiModelProperty(value = "online 1/ offline 3")
    @TableField(updateStrategy = FieldStrategy.IGNORED, insertStrategy = FieldStrategy.IGNORED)
    private String vnfStatus;

    @ApiModelProperty(value = "managed 1/ demanaged 0")
    @TableField(updateStrategy = FieldStrategy.IGNORED, insertStrategy = FieldStrategy.IGNORED)
    private String vnfManageStatus;

    @ApiModelProperty(value = "NE heartbeat report time point ")
    private Date heartBeatTime;

    @ApiModelProperty(value = "upper-layer NMS ID")
    @TableField(updateStrategy = FieldStrategy.IGNORED, insertStrategy = FieldStrategy.IGNORED)
    private String systemId;

    @ApiModelProperty (value = "0 | downloaded configuration, 1 | not download configuration")
    private String confStatus;

    @AllArgsConstructor
    @ToString
    public enum confStatusType {
        DOWLOADED (" 1 ", "has been download configuration"), UNDOWNLOADED (" 0 ", "not download configuration");
        @Getter
        private final String id;
        @Getter
        private final String name;
    }

    @AllArgsConstructor
    @ToString
    public enum vnfStatusType {
        ONLINE("1", "online "), OFFLINE("3"," offline ");
        @Getter
        private final String id;
        @Getter
        private final String name;
    }

    @AllArgsConstructor
    @ToString
    public enum vnfManageStatusType {
        MANAGED("1", "managed "), TUBE("0"," managed ");
        @Getter
        private final String id;
        @Getter
        private final String name;
    }
}