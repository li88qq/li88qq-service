package com.li88qq.admin.dao.menu;

import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageForm;
import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageVo;
import com.li88qq.bean.entity.admin.menu.Menu;
import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.enums.Format;
import com.li88qq.db.enums.If;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 菜单
 *
 * @author li88qq
 * @version 1.0 2023/7/20 21:46
 */
public interface MenuMapper {

    /**
     * 分页查询
     */
    @PageId
    @Select("select * from Menu :where order by id desc")
    @Condition("openType = :form.openType")
    @Condition(value = "createDate >= :form.beginDate", f = Format.TS_MIN)
    @Condition(value = "createDate <= :form.endDate", f = Format.TS_MAX)
    @Condition(value = "name like :form.name", f = Format.LIKE)
    @Condition(value = "href like :form.href", f = Format.LIKE)
    @Condition(value = "icon like :form.icon", f = Format.LIKE)
    @Condition(value = "(parentId = :form.parentId or find_in_set(:form.parentId,parentIds))",i = If.GT)
    Page<GetMenuPageVo> findPage(@Param("form") GetMenuPageForm form, Pageable pageable);

    /**
     * 根据id查询
     */
    @Select("select * from Menu where id = #{id}")
    Menu findById(@Param("id") Integer id);
}
