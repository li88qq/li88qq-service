package com.li88qq.main.dao.log;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.enums.Format;
import com.li88qq.main.dto.log.GetLoginLogPageForm;
import com.li88qq.main.dto.log.GetLoginLoginPageVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 登录记录
 *
 * @author li88qq
 * @version 1.0 2023/10/23 23:43
 */
public interface LoginLogMapper {

    /**
     * 分页查询
     */
    @Select("select * from LoginLog :where order by id desc")
    @Condition("uid = :uid")
    @Condition("state = :form.state")
    @Condition("errorState = :form.errorState")
    @Condition(value = "createDate >= :form.beginDate", f = Format.TS_MIN)
    @Condition(value = "createDate <= :form.endDate", f = Format.TS_MAX)
    @Condition(value = "ip like :form.ip", f = Format.LIKE)
    @PageId(countField = "id")
    Page<GetLoginLoginPageVo> getPage(@Param("uid") Integer uid, @Param("form") GetLoginLogPageForm form, Pageable pageable);
}
