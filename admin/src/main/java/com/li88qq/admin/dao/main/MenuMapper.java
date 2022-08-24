package com.li88qq.admin.dao.main;

import com.li88qq.admin.module.main.dto.menu.MenuListVo;
import com.li88qq.bean.entity.system.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单管理
 *
 * @author li88qq
 * @version 1.0 2022/8/22 22:49
 */
public interface MenuMapper {

    /**
     * 根据id查询
     */
    @Select("select * from Menu where id = #{id}")
    Menu findById(@Param("id") Integer id);

    /**
     * 查询列表
     */
    @Select("select * from Menu")
    List<MenuListVo> findList();
}
