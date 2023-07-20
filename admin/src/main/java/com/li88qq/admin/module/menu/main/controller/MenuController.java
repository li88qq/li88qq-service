package com.li88qq.admin.module.menu.main.controller;

import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageForm;
import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageVo;
import com.li88qq.admin.module.menu.main.dto.menu.SaveMenuForm;
import com.li88qq.admin.module.menu.main.service.MenuService;
import com.li88qq.db.dto.page.TPage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PatchMapping("/save")
    public void save(SaveMenuForm form){
        menuService.save(form);
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public TPage<GetMenuPageVo> page(GetMenuPageForm form) {
        return menuService.page(form);
    }
}
