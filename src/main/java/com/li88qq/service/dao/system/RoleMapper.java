package com.li88qq.service.dao.system;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.enums.Format;
import com.li88qq.service.module.system.dto.role.GetRolePageForm;
import com.li88qq.service.module.system.dto.role.GetRolePageVo;
import com.li88qq.service.bean.system.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author li88qq
 * @version 1.0 2024/1/1 16:51
 */
public interface RoleMapper {

    @Select("select * from Role where id = #{id}")
    Role findById(@Param("id")Integer id);

    @Select("select * from Role")
    List<Role> findList();

    /**
     * 更新是否授权全部菜单
     * @param id
     * @param isAllMenu
     * @return
     */
    @Update("update Role set isAllMenu = #{isAllMenu} where id = #{id}")
    int updateIsAllMenu(@Param("id") Integer id, @Param("isAllMenu") Integer isAllMenu);

    @Select("select * from Role :where order by id desc")
    @Condition("isAllMenu = :form.isAllMenu")
    @Condition(value = "name like :form.name",f = Format.LIKE)
    @PageId(countField = "id")
    Page<GetRolePageVo> getPage(@Param("form") GetRolePageForm form, Pageable pageable);
}
