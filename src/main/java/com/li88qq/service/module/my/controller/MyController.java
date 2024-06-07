package com.li88qq.service.module.my.controller;

import com.li88qq.service.module.my.dto.my.GetMyInfoVo;
import com.li88qq.service.module.my.dto.my.GetMyMenuListVo;
import com.li88qq.service.module.my.service.MyService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 我的
 *
 * @author li88qq
 * @version 1.0 2023/12/31 15:39
 */
@RestController
@RequestMapping("/my")
@Validated
public class MyController {

    @Resource
    private MyService myService;

    /**
     * 查询基本信息
     */
    @GetMapping("/info")
    public GetMyInfoVo getInfo() {
        return myService.getInfo();
    }

    /**
     * 查询菜单
     */
    @GetMapping("/menuList")
    public List<GetMyMenuListVo> getMenuList() {
        return myService.getMenuList();
    }
}
