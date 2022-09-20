package org.xgvela.oam.service.nfservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.mapper.nftube.OamVnfMapper;
import org.xgvela.oam.service.nfservice.InfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NfServiceImpl extends ServiceImpl<OamVnfMapper, OamVnf> implements InfService {
}
