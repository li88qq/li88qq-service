package com.li88qq.service.module.system.controller;

import com.li88qq.db.dto.page.TPage;
import com.li88qq.service.module.system.dto.user.*;
import com.li88qq.service.module.system.service.UserService;
import com.li88qq.service.config.web.response.BaseResponse;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author li88qq
 * @version 1.0 2023/12/29 23:00
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 新增
     */
    @PostMapping("/save")
    public BaseResponse save(@Valid @RequestBody SaveUserForm form) {
        return userService.save(form);
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public TPage<GetUserPageVo> getPage(GetUserPageForm form) {
        return userService.getPage(form);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public BaseResponse delete(@Valid @RequestBody UserIdForm form) {
        return userService.delete(form);
    }

    /**
     * 编辑
     */
    @PostMapping("/update")
    public BaseResponse update(@Valid @RequestBody UpdateUserForm form) {
        return userService.update(form);
    }

    /**
     * 查询信息
     */
    @GetMapping("/info")
    public GetUserInfoVo getInfo(@Valid UserIdForm form){
        return userService.getInfo(form);
    }

    /**
     * 查询详情
     */
    @GetMapping("/view")
    public GetUserViewVo getView(@Valid UserIdForm form){
        return userService.getView(form);
    }
}
