package com.li88qq.admin.dao.main;

import com.li88qq.admin.module.main.dto.role.RolePageForm;
import com.li88qq.admin.module.main.dto.role.RolePageVo;
import com.li88qq.bean.entity.system.Role;
import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.Format;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
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

    /**
     * 分页查询
     */
    @Select("select * from Role :where order by id desc")
    @Condition("name like :form.name")
    @Condition("remark like :form.remark")
    Page<RolePageVo> findPage(@Param("form") @Format RolePageForm form, Pageable pageable);
}
