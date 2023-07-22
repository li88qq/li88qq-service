package com.li88qq.admin.module.menu.main.controller;

import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageForm;
import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageVo;
import com.li88qq.admin.module.menu.main.dto.menu.SaveMenuForm;
import com.li88qq.admin.module.menu.main.service.MenuService;
import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.db.dto.page.TPage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单
 *
 * @author li88qq
 * @version 1.0 2023/7/19 22:24
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 新增
     */
    @PostMapping("/save")
    public BaseResponse save(SaveMenuForm form){
       return menuService.save(form);
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public TPage<GetMenuPageVo> page(GetMenuPageForm form) {
        return menuService.page(form);
    }
}
