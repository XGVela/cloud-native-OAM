package org.xgvela.oam.entity.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "DEBUG、INFO、WARN、ERROR。。。")
public class LogConfig {

    @ApiModelProperty(name = "",value = "maxFileSize",dataType = "String")
    private String maxFileSize;

    @ApiModelProperty(name = "",value = "maxHistory",dataType = "Integer")
    private Integer maxHistory;

    @ApiModelProperty(name = "",value = "totalSizeCap",dataType = "String")
    private String totalSizeCap;

}
