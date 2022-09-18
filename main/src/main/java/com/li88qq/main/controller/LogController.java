package com.li88qq.main.controller;

import com.li88qq.db.dto.TPage;
import com.li88qq.main.dto.log.ActionLogForm;
import com.li88qq.main.dto.log.ActionLogVo;
import com.li88qq.main.dto.log.LoginLogForm;
import com.li88qq.main.dto.log.LoginLogVo;
import com.li88qq.main.service.LogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 日记管理
 *
 * @author li88qq
 * @version 1.0 2022/9/8 23:20
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService logService;

    /**
     * 分页查询登录记录
     */
    @GetMapping("/loginLog")
    public TPage<LoginLogVo> loginLog(LoginLogForm form) {
        return logService.loginLog(form);
    }

    /**
     * 分页查询操作记录
     */
    @GetMapping("/actionLog")
    public TPage<ActionLogVo> actionLog(ActionLogForm form) {
        return logService.actionLog(form);
    }
}
