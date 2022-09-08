package com.li88qq.main.dao;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.Format;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
import com.li88qq.main.dto.log.LoginLogForm;
import com.li88qq.main.dto.log.LoginLogVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 登录记录
 *
 * @author li88qq
 * @version 1.0 2022/9/8 23:37
 */
public interface LoginLogMapper {

    /**
     * 分页查询登录记录
     */
    @Select("select * from LoginLog :where order by id desc")
    @Condition("uid = :uid")
    @Condition("state = :form.state")
    @Condition("errorCode = :form.errorCode")
    @Condition("createDate >= :form.beginDate")
    @Condition("createDate <= :form.endDate")
    @Condition("loginIp like :form.loginIp")
    Page<LoginLogVo> findPage(@Param("form") @Format LoginLogForm form, @Param("uid") Long uid, Pageable pageable);
}
