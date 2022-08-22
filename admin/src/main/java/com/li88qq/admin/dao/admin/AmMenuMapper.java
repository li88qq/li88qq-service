package com.li88qq.admin.dao.admin;

import com.li88qq.admin.module.admin.dto.ammenu.AmMenuListForm;
import com.li88qq.admin.module.admin.dto.ammenu.AmMenuListVo;
import com.li88qq.admin.module.admin.dto.ammenu.AmMenuTreeVo;
import com.li88qq.bean.entity.am.system.AmMenu;
import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.Format;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单
 *
 * @author li88qq
 * @version 1.0 2022/4/11 23:18
 */
public interface AmMenuMapper {

    /**
     * 根据id查询
     */
    @Select("select * from AmMenu where id = #{id}")
    AmMenu findById(@Param("id") Integer id);

    /**
     * 查询列表
     */
    @Select("select * from AmMenu :where")
    @Condition("name like :form.name")
    @Condition("state = :form.state")
    List<AmMenuListVo> findList(@Param("form") @Format AmMenuListForm form);

    /**
     * 查询树结构
     */
    @Select("select id,parentId,name,sort from AmMenu")
    List<AmMenuTreeVo> findTree();

    /**
     * 根据id列表删除
     */
    @Delete("delete from AmMenu :where")
    @Condition("id in :ids")
    int deleteByIds(@Param("ids") List<Long> ids);
}
