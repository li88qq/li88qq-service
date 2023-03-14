package com.li88qq.admin.dao.main;

import com.li88qq.admin.module.main.dto.user.UserPageForm;
import com.li88qq.admin.module.main.dto.user.UserPageVo;
import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.enums.Format;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户管理
 *
 * @author li88qq
 * @version 1.0 2022/4/4 23:16
 */
public interface UserMapper {

    /**
     * 分页查询用户列表
     */
    @Select("select * from User :where order by id desc")
    @Condition("state = :form.state")
    @Condition(value = "loginDate >= :form.loginDateBegin", f = Format.TS_MIN)
    @Condition(value = "loginDate <= :form.loginDateEnd", f = Format.TS_MAX)
    @Condition(value = "createDate >= :form.createDateBegin", f = Format.TS_MIN)
    @Condition(value = "createDate <= :form.createDateEnd", f = Format.TS_MAX)
    @Condition(value = "username like :form.username", f = Format.LIKE)
    @Condition(value = "name like :form.name", f = Format.LIKE)
    @Condition(value = "mobile like :form.mobile", f = Format.LIKE)
    @Condition(value = "loginIp like :form.loginIp",f = Format.LIKE)
    @PageId("id")
    Page<UserPageVo> findPage(@Param("form") UserPageForm form, Pageable pageable);
}
