package com.li88qq.main.dao.system;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色-菜单
 *
 * @author li88qq
 * @version 1.0 2024/1/4 21:56
 */
public interface Role_MenuMapper{

    /**
     * 根据角色id删除
     *
     * @param roleId
     * @return
     */
    @Delete("delete from Role_Menu where roleId = #{roleId}")
    int deleteByRole(@Param("roleId") Integer roleId);

    /**
     * 授权全部菜单
     *
     * @param roleId
     * @return
     */
    @Insert("insert ignore into Role_Menu (roleId,menuId) select #{roleId},id from Menu")
    int authAll(@Param("roleId") Integer roleId);

    /**
     * 授权菜单列表
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @Insert("<script>" +
            "insert ignore into Role_Menu (roleId,menuId) select #{roleId},id from Menu where id in" +
            "<foreach item='item' collection='menuIds' separator=\",\" open=\"(\" close=\")\" index=\"\">#{item}</foreach>" +
            "</script>")
    int authByMenuIds(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

    /**
     * 查询角色已授权菜单id列表
     *
     * @param roleId
     * @return
     */
    @Select("select distinct m.id from Role_Menu rm join Menu m on rm.menuId = m.id where rm.roleId = #{roleId}")
    List<Integer> findByRoleId(@Param("roleId") Integer roleId);
}
