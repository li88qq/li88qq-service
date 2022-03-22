package com.li88qq.login.dao;

import com.li88qq.bean.entity.system.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户
 *
 * @author li88qq
 * @version 1.0 2022/3/21 23:20
 */
public interface UserMapper {

    /**
     * 根据用户名查询用户
     */
    @Select("select * from User where username = #{username}")
    User findByUsername(@Param("username") String username);

    /**
     * 根据id查询用户
     */
    @Select("select * from User where id = #{id}")
    User findById(@Param("id") Long id);

}
