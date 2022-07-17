package com.li88qq.main.controller;

import com.li88qq.main.dto.my.BaseInfoVo;
import com.li88qq.main.dto.my.DetailVo;
import com.li88qq.main.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 我的
 *
 * @author li88qq
 * @version 1.0 2022/7/17 22:24
 */
@RestController
@RequestMapping("/my")
public class MyController {

    @Resource
    private MyService myService;

    /**
     * 查询基本信息
     */
    @GetMapping("/base")
    public BaseInfoVo base() {
        return myService.base();
    }

    /**
     * 查询详细信息
     */
    @GetMapping("/detail")
    public DetailVo detail() {
        return myService.detail();
    }
}
