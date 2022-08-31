package com.li88qq.admin.module.main.controller;

import com.li88qq.admin.module.main.dto.role.*;
import com.li88qq.admin.module.main.service.RoleService;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.db.dto.TPage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色管理
 *
 * @author li88qq
 * @version 1.0 2022/8/9 23:35
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 新增
     */
    @PostMapping("/save")
    public BaseResponse save(@RequestBody SaveRoleForm form) {
        return roleService.save(form);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public BaseResponse update(@RequestBody UpdateRoleForm form) {
        return roleService.update(form);
    }

    /**
     * 查询
     */
    @GetMapping("/find")
    public RoleVo find(@RequestParam Integer id) {
        return roleService.find(id);
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public TPage<RolePageVo> findPage(RolePageForm form) {
        return roleService.findPage(form);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody Integer id) {
        return roleService.delete(id);
    }
}
