package com.li88qq.service.controller;

import com.li88qq.service.constant.annitions.AcLog;
import com.li88qq.service.constant.enumeration.ActionType;
import com.li88qq.service.constant.enumeration.SaveStrategy;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.my.UpdatePasswordBo;
import com.li88qq.service.request.my.UpdateProfileBo;
import com.li88qq.service.response.GetProfileVo;
import com.li88qq.service.service.IMyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/my")
public class MyController {

    @Resource
    private IMyService myService;

    @PostMapping("/updatePassword")
    @AcLog(acType = ActionType.UPDATE, title = "修改密码")
    public BaseResponse updatePassword(@RequestBody @Valid UpdatePasswordBo bo) {
        return myService.updatePassword(bo);
    }

    /**
     * 修改个人信息
     *
     * @param bo
     * @return
     */
    @PostMapping("/updateProfile")
    @AcLog(acType = ActionType.UPDATE, title = "修改个人信息", detail = "bo")
    public BaseResponse updateProfile(@RequestBody @Valid UpdateProfileBo bo) {
        return myService.updateProfile(bo);
    }

    @GetMapping("/profile")
    public GetProfileVo getProfile() {
        return myService.getProfile();
    }

}