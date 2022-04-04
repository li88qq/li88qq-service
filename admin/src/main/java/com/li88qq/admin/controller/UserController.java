package com.li88qq.admin.controller;

import com.li88qq.admin.dto.user.UserPageForm;
import com.li88qq.admin.dto.user.UserPageVo;
import com.li88qq.admin.service.UserService;
import com.li88qq.db.dto.TPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户管理
 *
 * @author li88qq
 * @version 1.0 2022/4/3 23:00
 */
@RestController
@RequestMapping("/am/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 分页查询用户(后台)信息
     */
    @GetMapping("/page")
    public TPage<UserPageVo> getPage(UserPageForm form) {
        return userService.getPage(form);
    }
}
