package com.li88qq.service.controller;

import com.li88qq.service.response.MenuVo;
import com.li88qq.service.service.IMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单管理
 *
 * @author li88qq
 * @version 1.0 2021/10/20 23:27
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    /**
     * 获取菜单列表
     *
     * @return
     */
    @GetMapping("/list")
    public List<MenuVo> getList() {
        return menuService.getList();
    }

}
