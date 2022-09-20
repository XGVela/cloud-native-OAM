package org.xgvela.oam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.xgvela.oam.entity.log.EsLog;

import javax.servlet.http.HttpServletResponse;

public interface ILogService {

    IPage<EsLog.VO> list(Long current, Long size, EsLog.VO.Request entity);

    void export(Long current, Long size, EsLog.VO.Request entity,String exportType, HttpServletResponse response);

    Boolean del(EsLog.VO entity);

    String getKibanaUrl();
}
