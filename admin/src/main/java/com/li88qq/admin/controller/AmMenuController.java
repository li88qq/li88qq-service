package com.li88qq.admin.controller;

import com.li88qq.admin.dto.ammenu.*;
import com.li88qq.admin.service.AmMenuService;
import com.li88qq.bean.dto.IdsForm;
import com.li88qq.bean.web.response.BaseResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单管理(后台)
 *
 * @author li88qq
 * @version 1.0 2022/4/10 22:36
 */
@RestController
@RequestMapping("/ammenu")
public class AmMenuController {

    @Resource
    private AmMenuService amMenuService;

    /**
     * 保存菜单
     */
    @PostMapping("/save")
    public BaseResponse save(@RequestBody @Validated SaveAmMenuForm form) {
        return amMenuService.save(form);
    }

    /**
     * 更新菜单
     */
    @PostMapping("/update")
    public BaseResponse update(@RequestBody @Validated UpdateAmMenuForm form) {
        return amMenuService.update(form);
    }

    /**
     * 查询菜单
     */
    @GetMapping("/get")
    public AmMenuVo getMenu(@RequestParam Integer id) {
        return amMenuService.get(id);
    }

    /**
     * 查询列表
     */
    @GetMapping("/list")
    public List<AmMenuListVo> getList(AmMenuListForm form) {
        return amMenuService.getList(form);
    }

    /**
     * 查询树结构
     */
    @GetMapping("/tree")
    public List<AmMenuTreeVo> getTree() {
        return amMenuService.getTree();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public BaseResponse delete(@RequestBody IdsForm form) {
        return amMenuService.delete(form);
    }

}
