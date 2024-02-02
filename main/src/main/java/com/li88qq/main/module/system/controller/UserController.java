package com.li88qq.main.module.system.controller;


import com.li88qq.db.dto.page.TPage;
import com.li88qq.main.module.system.dto.user.*;
import com.li88qq.main.module.system.service.UserService;
import com.li88qq.publics.web.response.BaseResponse;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author li88qq
 * @version 1.0 2023/12/29 23:00
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/save")
    public BaseResponse save(@Valid @RequestBody SaveUserForm form) {
        return userService.save(form);
    }

    @GetMapping("/page")
    public TPage<GetUserPageVo> getPage(GetUserPageForm form) {
        return userService.getPage(form);
    }

    @PostMapping("/delete")
    public BaseResponse delete(@Valid @RequestBody UserIdForm form) {
        return userService.delete(form);
    }

    @PostMapping("/update")
    public BaseResponse update(@Valid @RequestBody UpdateUserForm form) {
        return userService.update(form);
    }

    @GetMapping("/info")
    public GetUserInfoVo getInfo(@Valid UserIdForm form){
        return userService.getInfo(form);
    }

    @GetMapping("/view")
    public GetUserViewVo getView(@Valid UserIdForm form){
        return userService.getView(form);
    }
}
