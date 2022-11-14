package org.xgvela.oam.entity.signaltrace;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "OamVnfSignaltrace", description = "OamVnfSignaltrace")
@Builder
public class OamVnfSignaltrace extends Model<OamVnfSignaltrace> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "taskId")
    @NonNull
    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    @ApiModelProperty(value = "taskName")
    @NotBlank(message = "taskName can't be null")
    private String taskName;

    @ApiModelProperty(value = "userType")
    @NotBlank(message = "dataType can't be null")
    private String dataType;

    @ApiModelProperty(value = "userNumber")
    private String userNumber;

    @ApiModelProperty(value = "interfaceType")
    private String interfaceType;

    @ApiModelProperty(value = "immediately tracking")
    private Boolean runNow;

    @ApiModelProperty(value = "tracing duration start")
    private Date startTime;

    @ApiModelProperty(value = "tracing duration end")
    private Date endTime;

    @ApiModelProperty(value = "tracing time/h")
    private String timeLength;

    @ApiModelProperty(value = "vnfType")
    @NotBlank(message = "callbackUrl can't be null")
    private String vnfType;

    @ApiModelProperty(value = "vnfInstanceId")
    private String vnfInstanceId;

    @ApiModelProperty(value = "vnfName")
    @NotBlank(message = "vnfName can't be null")
    private String vnfName;

    @ApiModelProperty(value = "vnfIp")
    private String vnfManageIp;

    @ApiModelProperty(value = "vnfPort")
    private String vnfSignalPort;

    @ApiModelProperty(value = "Status:UNDO, DOING, DONE")
    private String status;

    @ApiModelProperty(value = "The upper-layer system receives the address")
    @NotBlank(message = "callbackUrl can't be null")
    private String callbackUrl;

    @AllArgsConstructor
    @ToString
    public enum statusEnum {
        UNDO("UNDO"), DOING("DOING"), DONE("DONE");
        @Getter
        private final String name;
    }
}
