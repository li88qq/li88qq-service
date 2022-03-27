package com.li88qq.login.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 登录记录
 *
 * @author li88qq
 * @version 1.0 2022/3/25 20:39
 */
public interface AmLoginLogMapper {

    /**
     * 修改登出时间
     */
    @Update("update AmLoginLog set logoutDate = #{ut} where id = #{id} and uid = #{uid}")
    int updateLogoutDate(@Param("id") Long id, @Param("uid") Long uid, @Param("ut") Long ut);
}
