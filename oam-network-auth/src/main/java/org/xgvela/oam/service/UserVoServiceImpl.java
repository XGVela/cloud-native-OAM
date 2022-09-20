package org.xgvela.oam.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.xgvela.oam.entity.auth.UserVo;
import org.xgvela.oam.mapper.auth.UserVoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserVoServiceImpl extends ServiceImpl<UserVoMapper, UserVo> {

    @Autowired
    private UserVoMapper userVoMapper;

    public boolean create(UserVo userVo){
        if(userVo==null){
            return false;
        }
        return saveOrUpdate(userVo);
    }

    public boolean deleteByUserId(String userId){
        Map<String, Object> columnMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(userId)) {
            columnMap.put("user_id", userId);
            return removeByMap(columnMap);
        }
        return false;
    }
}
