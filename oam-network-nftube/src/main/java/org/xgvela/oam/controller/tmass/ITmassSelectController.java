package org.xgvela.oam.controller.tmass;

import org.xgvela.oam.entity.response.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/getNfResources")
public interface ITmassSelectController {

    @GetMapping("/{neType}/{neId}")
    RestResponse<Object> getNfResouce(@PathVariable(value = "neType") String neType, @PathVariable(value = "neId") String neId);

}

