package com.li88qq.admin.dao.main;

import com.li88qq.admin.module.main.dto.log.LoginLogForm;
import com.li88qq.admin.module.main.dto.log.LoginLogVo;
import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.enums.Format;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 登录记录
 *
 * @author li88qq
 * @version 1.0 2022/4/15 23:37
 */
public interface LoginLogMapper {

    /**
     * 分页查询
     */
    @Select("select log.*,u.username from LoginLog log left join User u on log.uid = u.id :where order by log.id desc")
    @Condition("log.state = :form.state")
    @Condition("log.errorCode = :form.errorCode")
    @Condition(value = "log.createDate >= :form.createDateBegin",f = Format.TS_MIN)
    @Condition(value = "log.createDate <= :form.createDateEnd", f = Format.TS_MIN)
    @Condition(value = "u.username like :form.username", f = Format.LIKE)
    @Condition(value = "u.loginIp like :form.loginIp", f = Format.LIKE)
    @PageId(countField = "log.id")
    Page<LoginLogVo> findPage(@Param("form") LoginLogForm form, Pageable pageable);
}
