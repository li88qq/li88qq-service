package com.li88qq.main.controller;

import com.li88qq.main.dto.other.GetIpVo;
import com.li88qq.main.service.OtherService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共接口
 *
 * @author li88qq
 * @version 1.0 2023/10/17 22:31
 */
@RestController
@RequestMapping("/o")
public class OtherController {

    @Resource
    private OtherService otherService;

    /**
     * 获取ip
     */
    @GetMapping("/ip")
    public GetIpVo getIp() {
        return otherService.getIp();
    }
}
