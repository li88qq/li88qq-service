package com.li88qq.service.module.system.service;

import com.li88qq.service.module.system.dto.appconfig.GetAppConfigForm;
import com.li88qq.service.module.system.dto.appconfig.GetAppConfigVo;
import com.li88qq.service.module.system.dto.appconfig.SaveAppConfigForm;
import com.li88qq.service.config.web.response.BaseResponse;

/**
 * 设置管理
 *
 * @author li88qq
 * @version 1.0 2024/1/20 10:42
 */
public interface SettingService {

    /**
     * 查询平台配置
     */
    GetAppConfigVo getAppConfig(GetAppConfigForm form);

    /**
     * 保存平台配置
     */
    BaseResponse saveAppConfig(SaveAppConfigForm form);
}
