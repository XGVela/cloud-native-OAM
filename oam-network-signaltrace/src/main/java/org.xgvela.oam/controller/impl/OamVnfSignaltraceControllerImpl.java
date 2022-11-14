package org.xgvela.oam.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.xgvela.oam.controller.IOamVnfSignaltraceController;
import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.response.ResponseFactory;
import org.xgvela.oam.entity.signaltrace.OamVnfSignaltrace;
import org.xgvela.oam.service.IOamVnfSignaltraceService;



@RestController
@AllArgsConstructor
public class OamVnfSignaltraceControllerImpl implements IOamVnfSignaltraceController {
    private final IOamVnfSignaltraceService oamVnfSignaltraceService;

    @Override
    public Response<OamVnfSignaltrace> createTask(OamVnfSignaltrace entity) {
        oamVnfSignaltraceService.save(entity);
        return ResponseFactory.getSuccessData(entity);
    }
}