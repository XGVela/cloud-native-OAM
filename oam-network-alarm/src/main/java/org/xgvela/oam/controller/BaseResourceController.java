package org.xgvela.oam.controller;

import com.inspur.cnet.common.core.entity.response.Response;
import com.inspur.cnet.common.core.entity.response.ResponseEnum;
import com.inspur.cnet.common.core.entity.response.ResponseFactory;
import com.inspur.cnet.security.entity.Developer;
import com.inspur.cnet.security.service.DeveloperServiceImpl;
import com.inspur.cnet.security.service.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author wangyongbo01
 */
@Slf4j
@RestController
public class BaseResourceController {

    @Autowired
    private DeveloperServiceImpl developerService;

    protected <T> Response resourceResponse(Function<String, T> function) {
        Developer developer = null;
        try {
            developer = developerService.getDeveloperByAdmin(SecurityUtils.getCurrentUserAccount());
        } catch (Exception e) {
        }
        String developId = Optional.ofNullable(developer).map(dev -> dev.getId()).orElse(null);

        T result = function.apply(developId);
        if (result == null) {
            return ResponseFactory.getSuccessData(null);
        } else if(result instanceof Response) {
            return (Response)result;
        } else if (result instanceof ResponseEnum) {
            return ResponseFactory.getResponse((ResponseEnum) result);
        } else if (result instanceof Boolean) {
            if ((Boolean)result) {
                return ResponseFactory.getSuccess();
            } else {
                return ResponseFactory.getError();
            }
        } else {
            return ResponseFactory.getSuccessData(result);
        }
    }
}