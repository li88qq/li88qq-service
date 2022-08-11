package com.li88qq.admin.module.main.controller;

import com.li88qq.admin.module.main.dto.menu.*;
import com.li88qq.admin.module.main.service.MenuService;
import com.li88qq.bean.web.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前台菜单管理
 *
 * @author li88qq
 * @version 1.0 2022/8/9 23:32
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 新增菜单
     */
    @PostMapping("/save")
    public BaseResponse save(SaveMenuForm form) {
        return menuService.save(form);
    }

    /**
     * 修改菜单
     */
    @PostMapping("/update")
    public BaseResponse update(UpdateMenuForm form) {
        return menuService.update(form);
    }

    /**
     * 查询菜单列表
     */
    @GetMapping("/list")
    public List<MenuListVo> findList() {
        return menuService.findList();
    }

    /**
     * 查询菜单
     */
    @GetMapping("/get")
    public MenuVo findMenu(Integer id) {
        return menuService.findMenu(id);
    }

}
