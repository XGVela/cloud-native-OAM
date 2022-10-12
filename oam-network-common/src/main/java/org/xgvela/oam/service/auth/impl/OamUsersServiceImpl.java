package org.xgvela.oam.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.xgvela.oam.entity.auth.OamUsers;
import org.xgvela.oam.mapper.auth.OamUsersMapper;
import org.xgvela.oam.service.auth.IOamUsersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class OamUsersServiceImpl extends ServiceImpl<OamUsersMapper, OamUsers> implements IOamUsersService {

}
