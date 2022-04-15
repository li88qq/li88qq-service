package com.li88qq.admin.dao.log;

import com.li88qq.admin.dto.log.LoginLogForm;
import com.li88qq.admin.dto.log.LoginLogVo;
import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.Format;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
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
    @Condition("log.createDate >= :form.createDateBegin")
    @Condition("log.createDate <= :form.createDateEnd")
    @Condition("u.username like :form.username")
    @Condition("u.loginIp like :form.loginIp")
    @PageId(countField = "log.id")
    Page<LoginLogVo> findPage(@Param("form") @Format LoginLogForm form, Pageable pageable);
}
