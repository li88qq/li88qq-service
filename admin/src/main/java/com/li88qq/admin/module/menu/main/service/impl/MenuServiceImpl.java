package com.li88qq.admin.module.menu.main.service.impl;

import com.li88qq.admin.dao.BaseMapper;
import com.li88qq.admin.dao.menu.MenuMapper;
import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageForm;
import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageVo;
import com.li88qq.admin.module.menu.main.dto.menu.SaveMenuForm;
import com.li88qq.admin.module.menu.main.service.MenuService;
import com.li88qq.bean.entity.admin.menu.Menu;
import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.common.web.response.ResponseCode;
import com.li88qq.common.web.response.ResponseUtil;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.dto.page.TPage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单
 *
 * @author li88qq
 * @version 1.0 2023/7/20 21:28
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private BaseMapper baseMapper;
    @Resource
    private MenuMapper menuMapper;

    /**
     * 新增
     */
    @Override
    @Transactional
    public BaseResponse save(SaveMenuForm form) {
        Menu menu = new Menu();
        String name = form.getName();
        String href = form.getHref();
        String icon = form.getIcon();

        menu.setName(name);
        menu.setName(href);
        menu.setName(icon);

        Integer parentId = form.getParentId();
        Integer openType = form.getOpenType();

        if (parentId != null && parentId > 0) {
            Menu parentMenu = menuMapper.findById(parentId);
            if (parentMenu == null) {
                ResponseUtil.exception(ResponseCode.FAIL);
            }
            String parentIds = parentMenu.getParentIds();
            if (parentIds == null) {
                parentIds = parentId.toString();
            } else {
                parentIds = String.join(",", parentIds, parentId.toString());
            }
            menu.setParentIds(parentIds);
            menu.setParentId(parentId);
        }
        if (openType != null && openType >= 0) {
            menu.setOpenType(openType);
        }
        baseMapper.insertNoId(menu);
        return ResponseUtil.ok();
    }

    /**
     * 分页查询菜单
     */
    @Override
    public TPage<GetMenuPageVo> page(GetMenuPageForm form) {
        Pageable pageable = new Pageable(form.getPage(), form.getPageSize());
        Page<GetMenuPageVo> pageVo = menuMapper.findPage(form, pageable);
        return pageVo.build();
    }
}
