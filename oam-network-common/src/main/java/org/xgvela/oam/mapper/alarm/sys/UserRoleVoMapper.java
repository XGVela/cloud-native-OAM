package org.xgvela.oam.mapper.alarm.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xgvela.oam.entity.alarm.sys.UserRoleVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * user-role mapping Mapper interface
 * </p>
 *
 * @author gengdawen00
 * @since 2021-11-1
 */
public interface UserRoleVoMapper extends BaseMapper<UserRoleVO> {

    /**
     * find user by userLogin
     * @param userLogin
     * @return
     */
    public UserRoleVO selectForUserRole(@Param(value = "userLogin") String userLogin);
}
