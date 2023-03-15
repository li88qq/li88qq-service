package com.li88qq.main.dao;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.enums.Format;
import com.li88qq.main.dto.log.ActionLogForm;
import com.li88qq.main.dto.log.ActionLogVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 操作记录
 *
 * @author li88qq
 * @version 1.0 2022/9/18 23:13
 */
public interface ActionLogMapper {

    /**
     * 分页查询操作记录
     */
    @Select("select * from ActionLog :where order by id desc")
    @Condition("uid = :uid")
    @Condition("actionType = :form.actionType")
    @Condition(value = "createDate >= :form.beginDate", f = Format.TS_MIN)
    @Condition(value = "createDate <= :form.endDate", f = Format.TS_MAX)
    @Condition(value = "title like :form.title", f = Format.LIKE)
    @Condition(value = "remark like :form.remark", f = Format.LIKE)
    @Condition(value = "ip like :form.ip", f = Format.LIKE)
    @PageId
    Page<ActionLogVo> findPage(@Param("form") ActionLogForm form, @Param("uid") Long uid, Pageable pageable);
}
