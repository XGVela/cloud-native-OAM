package org.xgvela.oam.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.xgvela.oam.entity.auth.OmcStrategyConf;
import org.xgvela.oam.mapper.auth.OmcStrategyConfMapper;
import org.xgvela.oam.service.auth.IOmcStrategyConfService;
import org.xgvela.oam.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * OmcStrategy Table ServiceImpl
 * </p>
 */
@Service
public class OmcStrategyConfServiceImpl extends ServiceImpl<OmcStrategyConfMapper, OmcStrategyConf> implements IOmcStrategyConfService {

    @Autowired
    private OmcStrategyConfMapper omcStrategyConfMapper;

    @Override
    public boolean batchEdit(JsonNode json) {
        List<OmcStrategyConf> omcStrategyConfList = JsonUtils.jn2o(json, new TypeReference<List<OmcStrategyConf>>() {
        });
        if (omcStrategyConfList != null) {
            for (OmcStrategyConf omcStrategyConf : omcStrategyConfList) {
                OmcStrategyConf entity = omcStrategyConfMapper.selectOne(new QueryWrapper<OmcStrategyConf>().eq("type", omcStrategyConf.getType()).eq("name", omcStrategyConf.getName()));
                omcStrategyConf.setId(entity.getId());
                omcStrategyConfMapper.updateById(omcStrategyConf);
            }
        }
        return true;
    }

    @Override
    public OmcStrategyConf findLoginTimeout(){
        return omcStrategyConfMapper.selectOne(new QueryWrapper<OmcStrategyConf>().eq("type", "loginTimeout"));
    }
}
