package com.li88qq.login.dao;

import com.li88qq.bean.entity.am.system.AmUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户
 *
 * @author li88qq
 * @version 1.0 2022/3/21 23:20
 */
public interface AmUserMapper {

    /**
     * 根据用户名查询用户
     */
    @Select("select * from AmUser where username = #{username}")
    AmUser findByUsername(@Param("username") String username);

    /**
     * 根据id查询用户
     */
    @Select("select * from AmUser where id = #{id}")
    AmUser findById(@Param("id") Long id);

    /**
     * 根据手机号码查询
     */
    @Select("select * from AmUser where mobile = #{mobile}")
    AmUser findByMobile(@Param("mobile") String mobile);

}
