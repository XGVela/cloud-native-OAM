package org.xgvela.oam.entity.alarm.analysis;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmAnalysisResultTopo {

    public String alarmId;

    public String children;

    public List<AlarmAnalysisResultTopo> childrens;

    @ApiModelProperty(value = "status")
    private Integer status;
}
