package com.li88qq.main.dao.system;

import com.li88qq.bean.beans.system.User;
import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.enums.Format;
import com.li88qq.main.module.system.dto.user.GetUserPageForm;
import com.li88qq.main.module.system.dto.user.GetUserPageVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author li88qq
 * @version 1.0 2023/12/30 23:16
 */
public interface UserMapper {

    @Select("select u.*,r.id roleId,r.name roleName from User u left join Role r on u.roleId = r.id " +
            ":where order by u.id desc")
    @Condition("r.id = :form.roleId")
    @Condition(value = "u.createDate >= :form.createDateBegin", f = Format.TS_MIN)
    @Condition(value = "u.createDate <= :form.createDateEnd", f = Format.TS_MAX)
    @Condition(value = "u.username like :form.username", f = Format.LIKE)
    @Condition(value = "u.nickname like :form.nickname", f = Format.LIKE)
    @Condition(value = "u.mobile like :form.mobile", f = Format.LIKE)
    @PageId(countField = "u.id")
    Page<GetUserPageVo> findPage(@Param("form") GetUserPageForm form, Pageable pageable);

    @Select("select * from User where id = #{id}")
    User findById(@Param("id") Integer id);
}
