package com.li88qq.service.module.system.controller;


import com.li88qq.service.module.system.dto.menu.*;
import com.li88qq.service.module.system.service.MenuService;
import com.li88qq.service.config.web.response.BaseResponse;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 菜单
 *
 * @author li88qq
 * @version 1.0 2023/12/26 22:17
 */
@RestController
@RequestMapping("/menu")
@Validated
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 新增
     */
    @PostMapping("/save")
    public BaseResponse save(@RequestBody @Valid SaveMenuForm form) {
        return menuService.save(form);
    }

    /**
     * 查询信息
     */
    @GetMapping("/info")
    public GetMenuInfoVo getInfo(@Valid MenuIdForm form) {
        return menuService.getInfo(form);
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public List<GetMenuPageVo> getPage(GetMenuPageForm form){
        return menuService.getPage(form);
    }

    /**
     * 查询列表
     */
    @GetMapping("/list")
    public List<GetMenuListVo> getList(){
        return menuService.getList();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public BaseResponse delete(@Valid @RequestBody MenuIdForm form) {
        return menuService.delete(form);
    }

    /**
     * 编辑
     */
    @PostMapping("/update")
    public BaseResponse update(@Valid @RequestBody UpdateMenuForm form) {
        return menuService.update(form);
    }

    /**
     * 详情
     */
    @GetMapping("/view")
    public GetMenuViewVo getView(@Valid MenuIdForm form){
        return menuService.getView(form);
    }
}
