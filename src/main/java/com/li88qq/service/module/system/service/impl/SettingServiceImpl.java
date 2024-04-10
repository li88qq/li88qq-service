package com.li88qq.service.module.system.service.impl;

import com.li88qq.service.bean.config.AppConfig;
import com.li88qq.service.dao.BaseMapper;
import com.li88qq.service.dao.system.AppConfigMapper;
import com.li88qq.service.module.system.dto.appconfig.GetAppConfigForm;
import com.li88qq.service.module.system.dto.appconfig.GetAppConfigVo;
import com.li88qq.service.module.system.dto.appconfig.SaveAppConfigForm;
import com.li88qq.service.module.system.service.SettingService;
import com.li88qq.service.constant.SysConfig;
import com.li88qq.service.utils.file.FileUtil;
import com.li88qq.service.utils.DateUtil;
import com.li88qq.service.config.web.response.BaseResponse;
import com.li88qq.service.config.web.response.ResponseUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 设置管理
 *
 * @author li88qq
 * @version 1.0 2024/1/20 10:47
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Resource
    private BaseMapper baseMapper;
    @Resource
    private AppConfigMapper appConfigMapper;

    /**
     * 查询平台配置
     */
    @Override
    public GetAppConfigVo getAppConfig(GetAppConfigForm form) {
        String appCode = form.getAppCode();
        if (appCode == null || appCode.isEmpty()) {
            appCode = SysConfig.APP_CODE;
        }
        AppConfig appConfig = appConfigMapper.findByCode(appCode);
        if (appConfig == null) {
            appConfig = new AppConfig();
        }
        GetAppConfigVo vo = new GetAppConfigVo();
        BeanUtils.copyProperties(appConfig, vo);

        // 处理资源名称
        vo.setIco(FileUtil.handleImageUrl(SysConfig.IMAGE_APP,vo.getIco()));
        vo.setLogo(FileUtil.handleImageUrl(SysConfig.IMAGE_APP,vo.getLogo()));
        vo.setBeiAnIco(FileUtil.handleImageUrl(SysConfig.IMAGE_APP,vo.getBeiAnIco()));

        return vo;
    }

    /**
     * 保存平台配置
     */
    @Override
    public BaseResponse saveAppConfig(SaveAppConfigForm form) {
        String appCode = form.getAppCode();
        AppConfig appConfig = appConfigMapper.findByCode(appCode);
        boolean save = false;
        if (appConfig == null) {
            appConfig = new AppConfig();
            save = true;
        }
        // 处理图片
        saveIco(form, appConfig);
        saveLogo(form, appConfig);
        saveBeiAnIco(form, appConfig);

        appConfig.setAppCode(form.getAppCode());
        appConfig.setAppName(form.getAppName());
        appConfig.setDomain(form.getDomain());
        appConfig.setBeiAn(form.getBeiAn());
        appConfig.setBeiAnLink(form.getBeiAnLink());
        appConfig.setIcp(form.getIcp());
        appConfig.setIcpLink(form.getIcpLink());
        appConfig.setCopyRight(form.getCopyRight());

        if (save) {
            baseMapper.insertNoId(appConfig);
        } else {
            appConfig.setUpdateDate(DateUtil.getTimestamp());
            baseMapper.update(appConfig);
        }

        return ResponseUtil.ok();
    }

    /**
     * 保存小图标
     */
    private void saveIco(SaveAppConfigForm form, AppConfig appConfig) {
        MultipartFile file = form.getIcoFile();
        if (file == null || file.isEmpty()) {
            appConfig.setIco(form.getIco());
            return;
        }
        //保存图片
        String fileName = "ico.jpg";
        String filePath = String.format("%s/%s", SysConfig.IMAGE_APP, fileName);
        FileUtil.save(file, filePath);
        appConfig.setIco(fileName);
    }

    /**
     * 保存logo
     */
    private void saveLogo(SaveAppConfigForm form, AppConfig appConfig) {
        MultipartFile file = form.getLogoFile();
        if (file == null || file.isEmpty()) {
            appConfig.setLogo(form.getLogo());
            return;
        }
        String fileName = "logo.jpg";
        String filePath = String.format("%s/%s", SysConfig.IMAGE_APP, fileName);
        FileUtil.save(file, filePath);
        appConfig.setLogo(fileName);
    }

    /**
     * 保存备案图片
     */
    private void saveBeiAnIco(SaveAppConfigForm form, AppConfig appConfig) {
        MultipartFile file = form.getBeiAnIcoFile();
        if (file == null || file.isEmpty()) {
            appConfig.setBeiAnIco(form.getBeiAnIco());
            return;
        }
        String fileName = "beiAn.jpg";
        String filePath = String.format("%s/%s", SysConfig.IMAGE_APP, fileName);
        FileUtil.save(file, filePath);
        appConfig.setBeiAnIco(fileName);
    }

}
