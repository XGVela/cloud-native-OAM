package org.xgvela.oam.mapper.alarm.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xgvela.oam.entity.alarm.sys.UserRoleVO;
import org.apache.ibatis.annotations.Param;

public interface UserRoleVoMapper extends BaseMapper<UserRoleVO> {

    public UserRoleVO selectForUserRole(@Param(value = "userLogin") String userLogin);
}
