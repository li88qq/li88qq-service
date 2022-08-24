package com.li88qq.admin.module.main.service.impl;

import com.li88qq.admin.dao.main.MenuMapper;
import com.li88qq.admin.module.main.dto.menu.MenuListVo;
import com.li88qq.admin.module.main.dto.menu.MenuVo;
import com.li88qq.admin.module.main.dto.menu.SaveMenuForm;
import com.li88qq.admin.module.main.dto.menu.UpdateMenuForm;
import com.li88qq.admin.module.main.service.MenuService;
import com.li88qq.bean.entity.system.Menu;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.bean.web.response.ResponseUtil;
import com.li88qq.db.core.BaseMapper;
import com.li88qq.utils.DateUtil;
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
    @Resource
    private MenuMapper menuMapper;

    /**
     * 新增菜单
     */
    @Override
    public BaseResponse save(SaveMenuForm form) {
        Integer parentId = form.getParentId();
        if (parentId == null || parentId < 0) {
            parentId = 0;
        }
        Menu menu = new Menu();
        menu.setName(form.getName());
        menu.setParentId(parentId);
        menu.setRouter(form.getRouter());
        menu.setUrl(form.getUrl());
        menu.setIcon(form.getIcon());

        baseMapper.save(menu);
        return ResponseUtil.ok();
    }

    /**
     * 修改菜单
     */
    @Override
    public BaseResponse update(UpdateMenuForm form) {
        Integer id = form.getId();
        Integer parentId = form.getParentId();
        if (parentId == null || parentId < 0) {
            parentId = 0;
        }
        Menu menu = new Menu();
        menu.setId(id);
        menu.setName(form.getName());
        menu.setParentId(parentId);
        menu.setRouter(form.getRouter());
        menu.setUrl(form.getUrl());
        menu.setIcon(form.getIcon());
        menu.setUpdateDate(DateUtil.getTimestamp());

        baseMapper.update(menu);
        return ResponseUtil.ok();
    }

    /**
     * 查询菜单列表
     */
    @Override
    public List<MenuListVo> findList() {
        return menuMapper.findList();
    }

    /**
     * 查询菜单
     */
    @Override
    public MenuVo findMenu(Integer id) {
        Menu menu = menuMapper.findById(id);
        MenuVo vo = new MenuVo();
        vo.setId(menu.getId());
        vo.setParentId(menu.getParentId());
        vo.setName(menu.getName());
        vo.setIcon(menu.getIcon());
        vo.setRouter(menu.getRouter());
        vo.setUrl(menu.getUrl());
        vo.setSort(menu.getSort());
        vo.setState(menu.getState());
        return vo;
    }
}
