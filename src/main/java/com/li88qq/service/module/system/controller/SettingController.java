package com.li88qq.service.module.system.controller;

import com.li88qq.service.module.system.dto.appconfig.GetAppConfigForm;
import com.li88qq.service.module.system.dto.appconfig.GetAppConfigVo;
import com.li88qq.service.module.system.dto.appconfig.SaveAppConfigForm;
import com.li88qq.service.module.system.service.SettingService;
import com.li88qq.service.config.web.response.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设置管理
 *
 * @author li88qq
 * @version 1.0 2024/1/20 10:39
 */
@RestController
@RequestMapping("/setting")
@Validated
public class SettingController {

    @Resource
    private SettingService settingService;

    /**
     * 查询平台配置
     */
    @GetMapping("/app/info")
    public GetAppConfigVo getAppConfig(GetAppConfigForm form) {
        return settingService.getAppConfig(form);
    }

    /**
     * 保存平台配置
     */
    @PostMapping(value = "/app/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse saveAppConfig(SaveAppConfigForm form) {
        return settingService.saveAppConfig(form);
    }

}
