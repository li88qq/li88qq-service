package com.li88qq.main.dao.setting;

import com.li88qq.bean.beans.config.AppConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 平台设置
 *
 * @author li88qq
 * @version 1.0 2024/1/20 10:48
 */
public interface AppConfigMapper {

    /**
     * 根据appCode查询
     */
    @Select("select * from AppConfig where appCode = #{appCode}")
    AppConfig findByCode(@Param("appCode") String appCode);
}
