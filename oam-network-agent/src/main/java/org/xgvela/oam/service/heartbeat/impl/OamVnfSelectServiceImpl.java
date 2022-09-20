package org.xgvela.oam.service.heartbeat.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.mapper.nftube.OamVnfMapper;
import org.xgvela.oam.service.heartbeat.IOamVnfSelectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OamVnfSelectServiceImpl extends ServiceImpl<OamVnfMapper, OamVnf> implements IOamVnfSelectService {
}
