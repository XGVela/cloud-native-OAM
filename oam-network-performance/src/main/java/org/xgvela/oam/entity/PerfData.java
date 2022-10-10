package org.xgvela.oam.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class PerfData implements Serializable {

    private String rmUID;
    private String resultTime;
    private List<String> measurementCategorys;
    private List<Object> measurementResults;
}
