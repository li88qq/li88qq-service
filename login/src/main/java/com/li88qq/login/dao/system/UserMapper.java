package com.li88qq.login.dao.system;

import com.li88qq.bean.beans.system.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author li88qq
 * @version 1.0 2023/12/31 11:08
 */
public interface UserMapper{

    @Select("select * from User where username = #{username}")
    User findByUsername(@Param("username")String username);

}
