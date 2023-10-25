package com.li88qq.main.dao.log;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.enums.Format;
import com.li88qq.main.dto.log.GetActionLogPageForm;
import com.li88qq.main.dto.log.GetActionLogPageVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 操作记录
 *
 * @author li88qq
 * @version 1.0 2023/10/23 23:43
 */
public interface ActionLogMapper {

    /**
     * 分页查询
     */
    @Select("select * from ActionLog :where order by id desc")
    @Condition("uid = :uid")
    @Condition("type = :type")
    @Condition(value = "createDate <= :form.beginDate", f = Format.TS_MIN)
    @Condition(value = "createDate >= :form.endDate", f = Format.TS_MAX)
    @Condition(value = "title like form.title", f = Format.LIKE)
    @Condition(value = "ip like form.ip", f = Format.LIKE)
    @Condition(value = "detail form.detail", f = Format.LIKE)
    @PageId(countField = "id")
    Page<GetActionLogPageVo> getPage(@Param("uid") Integer uid, @Param("form") GetActionLogPageForm form, Pageable pageable);
}
