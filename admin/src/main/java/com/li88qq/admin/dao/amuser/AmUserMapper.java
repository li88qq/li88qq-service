package com.li88qq.admin.dao.amuser;

import com.li88qq.admin.dto.amuser.AmUserInfo;
import com.li88qq.admin.dto.amuser.AmUserPageForm;
import com.li88qq.admin.dto.amuser.AmUserPageVo;
import com.li88qq.bean.entity.am.system.AmUser;
import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.Format;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
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
    @Condition("loginDate >= :form.loginDateBegin")
    @Condition("loginDate <= :form.loginDateEnd")
    @Condition("createDate >= :form.createDateBegin")
    @Condition("createDate <= :form.createDateEnd")
    @Condition("username like :form.username")
    @Condition("name like :form.name")
    @Condition("mobile like :form.mobile")
    @Condition("loginIp like :form.loginIp")
    Page<AmUserPageVo> findPage(@Param("form") @Format AmUserPageForm form, Pageable pageable);

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
