package org.xgvela.oam.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.databind.JsonNode;
import org.xgvela.oam.entity.auth.OmcStrategyConf;

/**
 * <p>
 * Policy configuration table Service class
 * </p>
 */
public interface IOmcStrategyConfService extends IService<OmcStrategyConf> {

    boolean batchEdit(JsonNode json);

    OmcStrategyConf findLoginTimeout();
}
