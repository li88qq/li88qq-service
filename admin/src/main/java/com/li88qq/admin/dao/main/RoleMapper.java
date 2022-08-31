package com.li88qq.admin.dao.main;

import com.li88qq.bean.entity.system.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 角色管理
 *
 * @author li88qq
 * @version 1.0 2022/8/28 23:50
 */
public interface RoleMapper {

    /**
     * 根据id查询
     */
    @Select("select * from Role where id = #{id}")
    Role findById(@Param("id") Integer id);

    /**
     * 根据id删除
     */
    @Delete("delete from Role where id = #{id}")
    int deleteById(@Param("id") Integer id);
}
