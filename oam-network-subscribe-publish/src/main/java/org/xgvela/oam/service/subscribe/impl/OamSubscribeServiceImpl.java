package org.xgvela.oam.service.subscribe.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import org.xgvela.oam.mapper.subscribe.OamSubscribeMapper;
import org.xgvela.oam.service.subscribe.IOamSubscribeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OamSubscribeServiceImpl extends ServiceImpl<OamSubscribeMapper, OamSubscribe> implements IOamSubscribeService {
}
