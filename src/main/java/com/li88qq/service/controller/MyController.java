package com.li88qq.service.controller;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.request.my.UpdatePasswordBo;
import com.li88qq.service.service.IMyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/my")
public class MyController {

    @Resource
    private IMyService myService;

    @PostMapping("/updatePassword")
    public BaseResponse updatePassword(UpdatePasswordBo bo){
        return myService.updatePassword(bo);
    }
}
