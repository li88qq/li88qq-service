package com.li88qq.admin.dao.amuser;

import com.li88qq.admin.dto.amuser.AmUserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户(后台)
 *
 * @author li88qq
 * @version 1.0 2022/3/28 17:54
 */
public interface AmUserMapper {

    /**
     * 查询用户(后台)信息
     */
    @Select("select username,head from AmUser where id = #{id}")
    AmUserInfo findInfo(@Param("id") Long id);
}
