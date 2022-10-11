package org.xgvela.oam.entity.alarm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ClearAlarm entity ", description = "clear alarm entity ")
@ToString
public class ClearAlarm {

    @ApiModelProperty(value = "NE ID")
    private String neId;

    @ApiModelProperty(value = "Alarm NE device type ")
    private String neType;

}