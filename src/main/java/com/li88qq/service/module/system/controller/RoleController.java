package com.li88qq.service.module.system.controller;

import com.li88qq.db.dto.page.TPage;
import com.li88qq.service.module.system.dto.role.*;
import com.li88qq.service.module.system.service.RoleService;
import com.li88qq.service.dto.form.IdForm;
import com.li88qq.service.config.web.response.BaseResponse;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * 角色
 *
 * @author li88qq
 * @version 1.0 2024/1/1 16:48
 */
@RestController
@RequestMapping("/role")
@Validated
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 查询列表
     */
    @GetMapping("/list")
    public List<GetRoleListVo>getList(){
        return roleService.getList();
    }

    /**
     * 新增
     */
    @PostMapping("/save")
    public BaseResponse save(@Valid @RequestBody SaveRoleForm form) {
        return roleService.save(form);
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public TPage<GetRolePageVo> getPage(GetRolePageForm form) {
        return roleService.getPage(form);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public BaseResponse delete(@Valid @RequestBody IdForm form) {
        return roleService.delete(form);
    }

    /**
     * 编辑
     */
    @PostMapping("/update")
    public BaseResponse update(@Valid @RequestBody UpdateRoleForm form) {
        return roleService.update(form);
    }

    /**
     * 查询信息
     */
    @GetMapping("/info")
    public GetRoleInfoVo getInfo(@Valid IdForm form){
        return roleService.getInfo(form);
    }

    /**
     * 查询详情
     */
    @GetMapping("/view")
    public GetRoleViewVo getView(@Valid IdForm form){
        return roleService.getView(form);
    }

    /**
     * 授权菜单
     */
    @PostMapping("/authMenu")
    public BaseResponse authMenu(@Valid @RequestBody AuthMenuForm form) {
        return roleService.authMenu(form);
    }

    /**
     * 查询授权菜单
     */
    @GetMapping("/menus")
    public GetAuthMenusVo getAuthMenus(@Valid IdForm form) {
        return roleService.getAuthMenus(form);
    }
}
