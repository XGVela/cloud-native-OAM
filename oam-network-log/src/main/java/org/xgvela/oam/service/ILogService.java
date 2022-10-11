package org.xgvela.oam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.xgvela.oam.entity.log.EsLog;

import javax.servlet.http.HttpServletResponse;

public interface ILogService {

    IPage<EsLog.VO.EsMessageVO> list(Long pageNum, Long pageSize, EsLog.VO.Request entity);

    void export(Long pageNum, Long pageSize, EsLog.VO.Request entity,String exportType, HttpServletResponse response);

    Boolean del(EsLog.VO.Request entity);

    String getKibanaUrl();
}
