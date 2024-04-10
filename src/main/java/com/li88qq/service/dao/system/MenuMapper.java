package com.li88qq.service.dao.system;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.enums.Format;
import com.li88qq.db.enums.If;
import com.li88qq.service.bean.system.Menu;
import com.li88qq.service.module.system.dto.menu.GetMenuPageForm;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单
 *
 * @author li88qq
 * @version 1.0 2023/12/16 12:22
 */
public interface MenuMapper {

    /**
     * 根据角色id查询
     */
    @Select("select m.* from Menu m join Role_Menu rm on m.id = rm.menuId " +
            "where rm.roleId = #{roleId} order by m.sort")
    List<Menu> findByRoleId(@Param("roleId") Integer roleId);

    /**
     * 查询全部
     */
    @Select("select * from Menu order by sort")
    List<Menu> findAll();

    /**
     * 根据上级节点删除
     */
    @Delete("delete from Menu where parentId = #{parentId} or find_in_set(#{parentId},parentIds)")
    int deleteByParentId(@Param("parentId") Integer parentId);

    @Select("select * from Menu :where order by sort")
    @Condition(value = "name like :form.name", f = Format.LIKE)
    @Condition(value = "(parentId = :form.parentId or find_in_set(:form.parentId,parentIds))", i = If.GT)
    List<Menu> selectList(@Param("form") GetMenuPageForm form);

    @Select("select * from Menu :where")
    @Condition("id in :ids")
    List<Menu> findList(@Param("ids") List<Integer> ids);

    @Select("select * from Menu where id = #{id}")
    Menu findById(@Param("id") Integer id);
}
