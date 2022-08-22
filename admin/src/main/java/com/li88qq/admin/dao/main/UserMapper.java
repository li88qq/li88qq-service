package com.li88qq.admin.dao.main;

import com.li88qq.admin.module.main.dto.user.UserPageForm;
import com.li88qq.admin.module.main.dto.user.UserPageVo;
import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.Format;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
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
    @Condition("loginDate >= :form.loginDateBegin")
    @Condition("loginDate <= :form.loginDateEnd")
    @Condition("createDate >= :form.createDateBegin")
    @Condition("createDate <= :form.createDateEnd")
    @Condition("username like :form.username")
    @Condition("name like :form.name")
    @Condition("mobile like :form.mobile")
    @Condition("loginIp like :form.loginIp")
    Page<UserPageVo> findPage(@Param("form") @Format UserPageForm form, Pageable pageable);
}
