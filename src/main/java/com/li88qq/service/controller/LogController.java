package com.li88qq.service.controller;

import com.li88qq.service.request.log.GetLoginPageBo;
import com.li88qq.service.response.GetLoginPageVo;
import com.li88qq.service.service.ILogService;
import org.fastquery.page.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 记录
 *
 * @author li88qq
 * @version 1.0 2021/8/5 22:02
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    private ILogService logService;

    /**
     * 分页查询登录记录列表
     *
     * @param bo
     * @return
     */
    @GetMapping("/loginPage")
    public Page<GetLoginPageVo> getLoginPage(GetLoginPageBo bo) {
        return logService.getLoginPage(bo);
    }
}
