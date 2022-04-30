package com.li88qq.admin.module.main.controller;

import com.li88qq.admin.module.main.dto.log.LoginLogForm;
import com.li88qq.admin.module.main.dto.log.LoginLogVo;
import com.li88qq.admin.module.main.service.LogService;
import com.li88qq.db.dto.TPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 记录
 *
 * @author li88qq
 * @version 1.0 2022/4/15 23:23
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService logService;

    /**
     * 登录记录
     */
    @GetMapping("/loginLog")
    public TPage<LoginLogVo> loginLog(LoginLogForm form) {
        return logService.loginLog(form);
    }

}
