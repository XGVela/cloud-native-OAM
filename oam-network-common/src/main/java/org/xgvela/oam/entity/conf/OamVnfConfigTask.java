package org.xgvela.oam.entity.conf;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 实体类
 * </p>
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OamVnfConfigTask实体", description = "")
public class OamVnfConfigTask extends Model<OamVnfConfigTask> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务ID")
    @TableId(type = IdType.AUTO)
    private Long taskId;

    @ApiModelProperty(value = "网元类型")
    private String neType;

    @ApiModelProperty(value = "网元实例ID")
    private String neId;

    @ApiModelProperty(value = "网元名称")
    private String vnfName;

    @ApiModelProperty(value = "网元IP")
    private String vnfManageIp;

    @ApiModelProperty(value = "网元端口")
    private String vnfSignalPort;

    @ApiModelProperty(value = "状态：未执行、已完成、执行中")
    private String status;

    @ApiModelProperty(value = "上层系统接收地址")
    private String callbackUrl;

    @ApiModelProperty(value = "任务创建时间")
    private Date createTime;

    @AllArgsConstructor
    @ToString
    public enum statusType {
        UNDO("UNDO", "未执行"), DOING("DOING", "执行中"), DONE("DONE", "已完成");
        @Getter
        private final String id;
        @Getter
        private final String name;
    }
}
