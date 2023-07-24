package com.li88qq.admin.module.system.admin.service.impl;

import com.li88qq.admin.dao.BaseMapper;
import com.li88qq.admin.dao.system.AppConfigMapper;
import com.li88qq.admin.module.system.admin.dto.config.*;
import com.li88qq.admin.module.system.admin.service.AppConfigService;
import com.li88qq.bean.entity.admin.system.AppConfig;
import com.li88qq.common.dto.BaseResult;
import com.li88qq.common.dto.IdIntForm;
import com.li88qq.common.utils.DateUtil;
import com.li88qq.common.utils.file.FileUtil;
import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.common.web.response.ResponseCode;
import com.li88qq.common.web.response.ResponseUtil;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.dto.page.TPage;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 应用设置
 *
 * @author li88qq
 * @version 1.0 2023/7/23 17:26
 */
@Service
public class AppConfigServiceImpl implements AppConfigService {

    @Resource
    private AppConfigMapper appConfigMapper;
    @Resource
    private BaseMapper baseMapper;

    private static final String FILE_PATH = "d:\\img\\";

    /**
     * 新增
     */
    @Override
    @Transactional
    public BaseResponse save(SaveForm form) {
        AppConfig appConfig = new AppConfig();
        appConfig.setDomain(form.getDomain());
        appConfig.setAppName(form.getAppName());

        String ico = saveImage(null, form.getIcoFile());
        String logo = saveImage(null, form.getLogoFile());
        String beiAnIco = saveImage(null, form.getBeiAnIcoFile());

        appConfig.setIco(ico);
        appConfig.setLogo(logo);
        appConfig.setBeiAnIco(beiAnIco);

        appConfig.setBeiAn(form.getBeiAn());
        appConfig.setBeiAnLink(form.getBeiAnLink());
        appConfig.setIcp(form.getIcp());
        appConfig.setIcpLink(form.getIcpLink());
        appConfig.setCopyRight(form.getCopyRight());

        baseMapper.insertNoId(appConfig);

        return ResponseUtil.ok();
    }

    /**
     * 保存图片
     *
     * @param value 已有图片
     * @param file  新图片
     * @return 图片路径
     */
    private String saveImage(String value, MultipartFile file) {
        if (value != null && !value.equals("")) {
            return value;
        }
        if (file != null && !file.isEmpty()) {
            BaseResult<String> result = FileUtil.save(file, FILE_PATH);
            if (result.isSuccess()) {
                return result.getData();
            }
        }
        return null;
    }

    /**
     * 编辑
     */
    @Override
    @Transactional
    public BaseResponse update(UpdateForm form) {
        Integer id = form.getId();
        AppConfig appConfig = appConfigMapper.findById(id);
        if (appConfig == null) {
            return ResponseUtil.error(ResponseCode.FAIL);
        }
        appConfig.setDomain(form.getDomain());
        appConfig.setAppName(form.getAppName());

        String ico = saveImage(form.getIco(), form.getIcoFile());
        String logo = saveImage(form.getLogo(), form.getLogoFile());
        String beiAnIco = saveImage(form.getBeiAnIco(), form.getBeiAnIcoFile());

        appConfig.setIco(ico);
        appConfig.setLogo(logo);
        appConfig.setBeiAnIco(beiAnIco);

        appConfig.setBeiAn(form.getBeiAn());
        appConfig.setBeiAnLink(form.getBeiAnLink());
        appConfig.setIcp(form.getIcp());
        appConfig.setIcpLink(form.getIcpLink());
        appConfig.setCopyRight(form.getCopyRight());

        appConfig.setUpdateDate(DateUtil.getTimestamp());

        baseMapper.update(appConfig);

        return ResponseUtil.ok();
    }

    /**
     * 删除
     */
    @Override
    @Transactional
    public BaseResponse delete(IdIntForm form) {
        appConfigMapper.deleteById(form.getId());
        return ResponseUtil.ok();
    }

    /**
     * 分页查询
     */
    @Override
    public TPage<GetPageVo> page(GetPageForm form) {
        Pageable pageable = new Pageable(form.getPage(), form.getPageSize());
        Page<GetPageVo> pageVo = appConfigMapper.findPage(form, pageable);
        return pageVo.build();
    }

    /**
     * 查询
     */
    @Override
    public GetVo get(IdIntForm form) {
        AppConfig config = appConfigMapper.findById(form.getId());
        GetVo vo = new GetVo();
        BeanUtils.copyProperties(config, vo);
        return vo;
    }

    /**
     * 详情
     */
    @Override
    public GetViewVo view(IdIntForm form) {
        AppConfig config = appConfigMapper.findById(form.getId());
        GetViewVo vo = new GetViewVo();
        BeanUtils.copyProperties(config, vo);
        return vo;
    }

    /**
     * 批量设置
     */
    @Override
    @Transactional
    public BaseResponse batchUpdate(BatchUpdateForm form) {
        List<Integer> ids = form.getIds();
        List<AppConfig> appConfigs = appConfigMapper.findList(ids);
        if (appConfigs.isEmpty()) {
            return ResponseUtil.error(ResponseCode.FAIL);
        }

        String ico = saveImage(null, form.getIcoFile());
        String logo = saveImage(null, form.getLogoFile());
        String beiAnIco = saveImage(null, form.getBeiAnIcoFile());

        String beiAn = form.getBeiAn();
        String beiAnLink = form.getBeiAnLink();
        String icp = form.getIcp();
        String icpLink = form.getIcpLink();
        String copyRight = form.getCopyRight();

        appConfigs.forEach(item -> {
            if (ico != null) {
                item.setIco(ico);
            }
            if (logo != null) {
                item.setLogo(logo);
            }
            if (beiAnIco != null) {
                item.setBeiAnIco(beiAnIco);
            }
            if (beiAn != null && !beiAn.equals("")) {
                item.setBeiAn(beiAn);
            }
            if (beiAnLink != null && !beiAnLink.equals("")) {
                item.setBeiAnLink(beiAnLink);
            }
            if (icp != null && !icp.equals("")) {
                item.setIcp(icp);
            }
            if (icpLink != null && !icpLink.equals("")) {
                item.setIcpLink(icpLink);
            }
            if (copyRight != null && !copyRight.equals("")) {
                item.setCopyRight(copyRight);
            }
        });

        baseMapper.updateList(appConfigs);

        return ResponseUtil.ok();
    }
}
