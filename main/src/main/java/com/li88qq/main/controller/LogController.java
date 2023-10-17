package com.li88qq.main.controller;

import com.li88qq.db.dto.page.TPage;
import com.li88qq.main.dto.log.GetActionLogPageForm;
import com.li88qq.main.dto.log.GetActionLogPageVo;
import com.li88qq.main.dto.log.GetLoginLogPageForm;
import com.li88qq.main.dto.log.GetLoginLoginPageVo;
import com.li88qq.main.service.LogService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日记
 *
 * @author li88qq
 * @version 1.0 2023/10/17 22:42
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService logService;

    /**
     * 分页查询登录记录
     */
    @GetMapping("/login")
    public TPage<GetLoginLoginPageVo> getLoginPage(GetLoginLogPageForm form) {
        return logService.getLoginPage(form);
    }

    /**
     * 分页查询操作记录
     */
    @GetMapping("/action")
    public TPage<GetActionLogPageVo> getActionPage(GetActionLogPageForm form) {
        return logService.getActionPage(form);
    }
}
