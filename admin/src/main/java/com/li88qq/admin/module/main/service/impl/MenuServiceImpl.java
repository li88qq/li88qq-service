package com.li88qq.admin.module.main.service.impl;

import com.li88qq.admin.module.main.dto.menu.MenuListVo;
import com.li88qq.admin.module.main.dto.menu.MenuVo;
import com.li88qq.admin.module.main.dto.menu.SaveMenuForm;
import com.li88qq.admin.module.main.dto.menu.UpdateMenuForm;
import com.li88qq.admin.module.main.service.MenuService;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.db.core.BaseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单服务
 *
 * @author li88qq
 * @version 1.0 2022/8/11 23:38
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private BaseMapper baseMapper;

    /**
     * 新增菜单
     */
    @Override
    public BaseResponse save(SaveMenuForm form) {
        return null;
    }

    /**
     * 修改菜单
     */
    @Override
    public BaseResponse update(UpdateMenuForm form) {
        return null;
    }

    /**
     * 查询菜单列表
     */
    @Override
    public List<MenuListVo> findList() {
        return null;
    }

    /**
     * 查询菜单
     */
    @Override
    public MenuVo findMenu(Integer id) {
        return null;
    }
}
