package com.li88qq.admin.module.main.service;

import com.li88qq.admin.module.main.dto.menu.MenuListVo;
import com.li88qq.admin.module.main.dto.menu.MenuVo;
import com.li88qq.admin.module.main.dto.menu.SaveMenuForm;
import com.li88qq.admin.module.main.dto.menu.UpdateMenuForm;
import com.li88qq.bean.web.response.BaseResponse;

import java.util.List;

/**
 * 菜单服务
 *
 * @author li88qq
 * @version 1.0 2022/8/11 23:29
 */
public interface MenuService {

    /**
     * 新增菜单
     */
    BaseResponse save(SaveMenuForm form);

    /**
     * 修改
     */
    BaseResponse update(UpdateMenuForm form);

    /**
     * 查询列表
     */
    List<MenuListVo> findList();

    /**
     * 查询菜单
     */
    MenuVo findMenu(Integer id);
}
