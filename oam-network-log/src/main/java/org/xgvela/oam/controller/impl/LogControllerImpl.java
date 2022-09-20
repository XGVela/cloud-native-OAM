package org.xgvela.oam.controller.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.inspur.cnet.common.core.entity.response.Response;
import com.inspur.cnet.common.core.entity.response.ResponseFactory;
import org.xgvela.oam.controller.ILogController;
import org.xgvela.oam.entity.log.EsLog;
import org.xgvela.oam.service.ILogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class LogControllerImpl implements ILogController {
    private final ILogService logService;

    @Override
    public Response<IPage<EsLog.VO>> list(Long current, Long size, EsLog.VO.Request entity) {
        return ResponseFactory.getSuccessData(logService.list(current, size, entity));
    }

    @Override
    public void export(Long current, Long size, EsLog.VO.Request entity, String exportType, HttpServletResponse response) {
        logService.export(current, size, entity, exportType, response);
    }

    @Override
    public Response<Boolean> del(EsLog.VO entity) {
        return ResponseFactory.getSuccessData(logService.del(entity));
    }

    @Override
    public Response<String> kibanaUrl() {
        return ResponseFactory.getSuccessData(logService.getKibanaUrl());
    }
}
