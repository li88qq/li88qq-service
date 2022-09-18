package com.li88qq.main.dao;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.Format;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
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
    @Condition("createDate >= :form.beginDate")
    @Condition("createDate <= :form.endDate")
    @Condition("title like :form.title")
    @Condition("remark like :form.remark")
    @Condition("ip like :form.ip")
    Page<ActionLogVo> findPage(@Param("form") @Format ActionLogForm form, @Param("uid") Long uid, Pageable pageable);
}
