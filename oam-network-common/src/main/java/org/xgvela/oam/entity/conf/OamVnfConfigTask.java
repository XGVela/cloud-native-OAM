package org.xgvela.oam.entity.conf;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OamVnfConfigTask entity ", description = "")
public class OamVnfConfigTask extends Model<OamVnfConfigTask> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "task ID")
    @TableId(type = IdType.AUTO)
    private Long taskId;

    @ApiModelProperty(value = "NE type ")
    private String neType;

    @ApiModelProperty(value = "NE instance ID")
    private String neId;

    @ApiModelProperty(value = "NE name ")
    private String vnfName;

    @ApiModelProperty(value = "NE IP address ")
    private String vnfManageIp;

    @ApiModelProperty(value = "NE port ")
    private String vnfSignalPort;

    @ApiModelProperty(value = "Status: not executed, completed, executing ")
    private String status;

    @ApiModelProperty(value = "upper system receiving address ")
    private String callbackUrl;

    @ApiModelProperty(value = "task creation time ")
    private Date createTime;

    @ApiModelProperty(value = "task type ")
    private String type;

    @ApiModelProperty(value = "the version involved in the task ")
    private String version;

    @AllArgsConstructor
    @ToString
    public enum statusType {
        UNDO (" UNDO ", "UNDO") , DOING (" DOING ", "DOING") , DONE (" DONE ", "DONE");
        @Getter
        private final String id;
        @Getter
        private final String name;
        }
}