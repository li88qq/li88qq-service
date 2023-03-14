package com.li88qq.admin.dao.admin;

import com.li88qq.admin.module.admin.dto.amuser.AmUserInfo;
import com.li88qq.admin.module.admin.dto.amuser.AmUserPageForm;
import com.li88qq.admin.module.admin.dto.amuser.AmUserPageVo;
import com.li88qq.bean.entity.am.system.AmUser;
import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.enums.Format;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户(后台)
 *
 * @author li88qq
 * @version 1.0 2022/3/28 17:54
 */
public interface AmUserMapper {

    /**
     * 查询用户(后台)信息
     */
    @Select("select username,head from AmUser where id = #{id}")
    AmUserInfo findInfo(@Param("id") Long id);

    /**
     * 分页查询用户(后台)列表
     */
    @Select("select * from AmUser :where order by id desc")
    @Condition("state = :form.state")
    @Condition(value = "loginDate >= :form.loginDateBegin", f = Format.TS_MIN)
    @Condition(value = "loginDate <= :form.loginDateEnd", f = Format.TS_MAX)
    @Condition(value = "createDate >= :form.createDateBegin", f = Format.TS_MIN)
    @Condition(value = "createDate <= :form.createDateEnd", f = Format.TS_MAX)
    @Condition(value = "username like :form.username", f = Format.LIKE)
    @Condition(value = "name like :form.name", f = Format.LIKE)
    @Condition(value = "mobile like :form.mobile", f = Format.LIKE)
    @Condition(value = "loginIp like :form.loginIp", f = Format.LIKE)
    @PageId
    Page<AmUserPageVo> findPage(@Param("form") AmUserPageForm form, Pageable pageable);

    /**
     * 根据用户名查询用户
     */
    @Select("select * from AmUser where username = #{username}")
    AmUser findByUsername(@Param("username") String username);

    /**
     * 根据手机号码查询用户
     */
    @Select("select * from AmUser where mobile = #{mobile}")
    AmUser findByMobile(@Param("mobile") String mobile);

}
