package com.li88qq.admin.controller;

import com.li88qq.admin.dto.amuser.AddAmUserForm;
import com.li88qq.admin.dto.amuser.AmUserInfo;
import com.li88qq.admin.dto.amuser.AmUserPageForm;
import com.li88qq.admin.dto.amuser.AmUserPageVo;
import com.li88qq.admin.service.AmUserService;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.db.dto.TPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户(后台)管理
 *
 * @author li88qq
 * @version 1.0 2022/3/28 17:49
 */
@RestController
@RequestMapping("/am/amUser")
public class AmUserController {

    @Resource
    private AmUserService amUserService;

    /**
     * 查询用户(后台)基本信息
     */
    @GetMapping("/info")
    public AmUserInfo getInfo() {
        return amUserService.getInfo();
    }

    /**
     * 分页查询用户(后台)信息
     */
    @GetMapping("/page")
    public TPage<AmUserPageVo> getPage(AmUserPageForm form) {
        return amUserService.getPage(form);
    }

    /**
     * 添加用户(后台)
     */
    @PostMapping("/add")
    public BaseResponse addAmUser(AddAmUserForm form) {
        return amUserService.add(form);
    }
}
