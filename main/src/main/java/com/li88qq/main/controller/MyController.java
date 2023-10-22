package com.li88qq.main.controller;

import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.main.dto.my.*;
import com.li88qq.main.service.MyService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 我的
 *
 * @author li88qq
 * @version 1.0 2023/10/17 22:29
 */
@RestController
@RequestMapping("/my")
public class MyController {

    @Resource
    private MyService myService;

    /**
     * 获取我的信息
     */
    @GetMapping("/info")
    public GetMyInfoVo getInfo() {
        return myService.getInfo();
    }

    /**
     * 获取我的个人资料
     */
    @GetMapping("/profile")
    public GetProfileVo getProfile() {
        return myService.getProfile();
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePassword")
    public BaseResponse updatePassword(@RequestBody UpdatePasswordForm form) {
        return myService.updatePassword(form);
    }

    /**
     * 修改手机号码
     */
    @PostMapping("/updateMobile")
    public BaseResponse updateMobile(@RequestBody UpdateMobileForm form) {
        return myService.updateMobile(form);
    }

    /**
     * 修改个人资料
     */
    @PostMapping("/updateProfile")
    public BaseResponse updateProfile(UpdateProfileForm form) {
        return myService.updateProfile(form);
    }

    /**
     * 获取菜单列表
     */
    @GetMapping("/menus")
    public List<GetMenuListVo> getMenuList() {
        return myService.getMenuList();
    }

    /**
     * 获取动作列表
     */
    @GetMapping("/actions")
    public List<GetActionList> getActionList() {
        return myService.getActionList();
    }
}
