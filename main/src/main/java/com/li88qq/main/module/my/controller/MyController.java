package com.li88qq.main.module.my.controller;

import com.li88qq.main.module.my.dto.my.GetMyInfoVo;
import com.li88qq.main.module.my.dto.my.GetMyMenuListVo;
import com.li88qq.main.module.my.service.MyService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author li88qq
 * @version 1.0 2023/12/31 15:39
 */
@RestController
@RequestMapping("/my")
@Validated
public class MyController {

    @Resource
    private MyService myService;

    @GetMapping("/info")
    public GetMyInfoVo getInfo() {
        return myService.getInfo();
    }

    @GetMapping("/menuList")
    public List<GetMyMenuListVo> getMenuList() {
        return myService.getMenuList();
    }
}
