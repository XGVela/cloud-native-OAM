package org.xgvela.oam.service;

import org.xgvela.oam.entity.PerfData;
import java.util.List;

public interface IPerformanceService {

    List<PerfData> listQuery(String apiVersion, String elementTypeValue, String objectTypeValue, String rmUIDs, String measurementCategorys);
}
