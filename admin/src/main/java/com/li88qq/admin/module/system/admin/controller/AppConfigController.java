package com.li88qq.admin.module.system.admin.controller;

import com.li88qq.admin.module.system.admin.dto.config.*;
import com.li88qq.admin.module.system.admin.service.AppConfigService;
import com.li88qq.common.dto.IdIntForm;
import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.db.dto.page.TPage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用设置
 *
 * @author li88qq
 * @version 1.0 2023/7/23 16:50
 */
@RestController
@RequestMapping("/app/config")
public class AppConfigController {

    @Resource
    private AppConfigService appConfigService;

    /**
     * 新增
     */
    @PostMapping("/save")
    public BaseResponse save(SaveForm form) {
        return appConfigService.save(form);
    }

    /**
     * 编辑
     */
    @PostMapping("/update")
    public BaseResponse update(UpdateForm form) {
        return appConfigService.update(form);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public BaseResponse delete(IdIntForm form) {
        return appConfigService.delete(form);
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public TPage<GetPageVo> page(GetPageForm form) {
        return appConfigService.page(form);
    }

    /**
     * 查询
     */
    @GetMapping("/get")
    public GetVo get(IdIntForm form) {
        return appConfigService.get(form);
    }

    /**
     * 详情
     */
    @GetMapping("/view")
    public GetViewVo view(IdIntForm form) {
        return appConfigService.view(form);
    }

    /**
     * 批量设置
     */
    @PostMapping("/batchUpdate")
    public BaseResponse batchUpdate(BatchUpdateForm form) {
        return appConfigService.batchUpdate(form);
    }
}
