package org.xgvela.oam.controller.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;

import org.xgvela.oam.controller.ILogController;
import org.xgvela.oam.entity.log.EsLog;
import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.response.ResponseFactory;
import org.xgvela.oam.service.ILogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class LogControllerImpl implements ILogController {
    private final ILogService logService;

    @Override
    public Response<IPage<EsLog.VO.EsMessageVO>> list(Long pageNum, Long pageSize, EsLog.VO.Request entity) {
        return ResponseFactory.getSuccessData(logService.list(pageNum, pageSize, entity));
    }

    @Override
    public void export(Long pageNum, Long pageSize, EsLog.VO.Request entity, String exportType, HttpServletResponse response) {
        logService.export(pageNum, pageSize, entity, exportType, response);
    }

    @Override
    public Response<Boolean> del(EsLog.VO.Request entity) {
        return ResponseFactory.getSuccessData(logService.del(entity));
    }

    @Override
    public Response<String> kibanaUrl() {
        return ResponseFactory.getSuccessData(logService.getKibanaUrl());
    }
}
