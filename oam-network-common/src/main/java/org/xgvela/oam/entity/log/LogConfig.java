package org.xgvela.oam.entity.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Log configuration, size unit: KB, MB, GB, log level: DEBUG, INFO, WARN, ERROR..." )
public class LogConfig {

    @ApiModelProperty(name = "",value = "log file size, default 100MB",dataType = "String")
    private String maxFileSize;

    @ApiModelProperty(name = "",value = "Number of days to keep log file, default 90 days ",dataType = "Integer")
    private Integer maxHistory;

    @ApiModelProperty(name = "",value = "Total log file size, default 20GB",dataType = "String")
    private String totalSizeCap;

}