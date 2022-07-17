package com.li88qq.main.dao;

import com.li88qq.bean.entity.system.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户
 *
 * @author li88qq
 * @version 1.0 2022/7/17 23:03
 */
public interface UserMapper {

    @Select("select * from User where id = #{id}")
    User findById(@Param("id") Long id);
}
