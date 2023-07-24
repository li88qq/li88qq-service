package com.li88qq.admin.dao.system;

import com.li88qq.admin.module.system.admin.dto.config.GetPageForm;
import com.li88qq.admin.module.system.admin.dto.config.GetPageVo;
import com.li88qq.bean.entity.admin.system.AppConfig;
import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.enums.Format;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 应用设置
 *
 * @author li88qq
 * @version 1.0 2023/7/23 17:29
 */
public interface AppConfigMapper {

    /**
     * 查询
     */
    @Select("select * from AppConfig where id = #{id}")
    AppConfig findById(@Param("id") Integer id);

    /**
     * 根据id删除
     */
    @Delete("delete from AppConfig where id = #{id}")
    int deleteById(@Param("id") Integer id);

    /**
     * 分页查询
     */
    @PageId
    @Select("select * from AppConfig :where order by id desc")
    @Condition(value = "domain like :form.domain", f = Format.LIKE)
    @Condition(value = "appName like :form.appName", f = Format.LIKE)
    Page<GetPageVo> findPage(GetPageForm form, Pageable pageable);

    /**
     * 根据id列表查询
     */
    @Select("select * from AppConfig :where")
    @Condition("id in (:ids)")
    List<AppConfig> findList(@Param("ids") List<Integer> ids);
}
