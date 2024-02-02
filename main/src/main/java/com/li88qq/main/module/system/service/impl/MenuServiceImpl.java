package com.li88qq.main.module.system.service.impl;

import com.li88qq.bean.beans.system.Menu;
import com.li88qq.db.utils.BeanUtil;
import com.li88qq.main.dao.BaseMapper;
import com.li88qq.main.dao.system.MenuMapper;
import com.li88qq.main.module.system.dto.menu.*;
import com.li88qq.main.module.system.service.MenuService;
import com.li88qq.publics.utils.DateUtil;
import com.li88qq.publics.web.response.BaseResponse;
import com.li88qq.publics.web.response.ResponseCode;
import com.li88qq.publics.web.response.ResponseException;
import com.li88qq.publics.web.response.ResponseUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单
 *
 * @author li88qq
 * @version 1.0 2023/12/16 17:09
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private BaseMapper baseMapper;

    /**
     * 新增
     */
    @Override
    @Transactional
    public BaseResponse save(SaveMenuForm form) {
        Menu menu = new Menu();
        Integer parentId = form.getParentId();
        if (parentId == null || parentId < 0) {
            parentId = 0;
        }

        String href = form.getHref();
        if (href != null && !href.isEmpty()) {
            boolean isHref = href.startsWith("http") || href.startsWith("/");
            if (!isHref) {
                return ResponseUtil.error("路由应该http或者/开头");
            }
        }

        String parentIds = null;
        if (parentId > 0) {
            Menu parent = menuMapper.findById(parentId);
            if (parent == null) {
                return ResponseUtil.error(ResponseCode.DATA);
            }
            parentIds = parent.getParentIds();
            if (parentIds == null || parentIds.isEmpty()) {
                parentIds = parent.getId().toString();
            } else {
                parentIds = String.format("%s,%d", parent.getParentIds(), parentId);
            }
        }

        Integer openType = form.getOpenType();
        if (openType != null && openType > 0) {
            menu.setOpenType(openType);
        }
        Integer sort = form.getSort();
        if (sort != null && sort > 0) {
            menu.setSort(sort);
        }
        menu.setName(form.getName());
        menu.setHref(href);
        menu.setIcon(form.getIcon());
        menu.setParentId(parentId);
        menu.setParentIds(parentIds);

        baseMapper.insertNoId(menu);
        return ResponseUtil.ok();
    }

    /**
     * 查询信息
     */
    @Override
    public GetMenuInfoVo getInfo(MenuIdForm form) {
        Menu menu = menuMapper.findById(form.getId());
        if (menu == null) {
            throw new ResponseException(ResponseCode.DATA);
        }
        GetMenuInfoVo vo = new GetMenuInfoVo();
        BeanUtils.copyProperties(menu, vo);
        return vo;
    }

    /**
     * 分页查询
     */
    @Override
    public List<GetMenuPageVo> getPage(GetMenuPageForm form) {
        List<Menu> menus = menuMapper.selectList(form);
        List<GetMenuPageVo> voList = menus.stream().map(item -> {
            GetMenuPageVo vo = new GetMenuPageVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
        return voList;
    }

    /**
     * 查询列表
     */
    @Override
    public List<GetMenuListVo> getList() {
        List<Menu> menus = menuMapper.findAll();
        return menus.stream().map(item -> {
            GetMenuListVo vo = new GetMenuListVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 删除
     */
    @Override
    public BaseResponse delete(MenuIdForm form) {
        Integer id = form.getId();
        Menu menu = menuMapper.findById(id);
        if (menu == null) {
            return ResponseUtil.error(ResponseCode.DATA);
        }
        baseMapper.delete(menu);
        //同时删除子菜单
        menuMapper.deleteByParentId(id);

        return ResponseUtil.ok();
    }

    /**
     * 编辑
     */
    @Override
    public BaseResponse update(UpdateMenuForm form) {
        Integer id = form.getId();

        String href = form.getHref();
        if (href != null && !href.isEmpty()) {
            boolean isHref = href.startsWith("http") || href.startsWith("/");
            if (!isHref) {
                return ResponseUtil.error("路由应该http或者/开头");
            }
        }

        Menu menu = menuMapper.findById(id);
        if (menu == null) {
            return ResponseUtil.error(ResponseCode.DATA);
        }
        Integer openType = form.getOpenType();
        if (openType == null || openType < 0) {
            openType = 0;
        }
        Integer keepalive = form.getKeepalive();
        if (keepalive == null || keepalive != 1) {
            keepalive = 0;
        }
        Integer sort = form.getSort();
        if (sort == null || sort < 0) {
            sort = 0;
        }

        Menu updatePo = BeanUtil.reset(Menu.class);
        updatePo.setId(id);
        updatePo.setName(form.getName());
        updatePo.setHref(form.getHref());
        updatePo.setIcon(form.getIcon());
        updatePo.setOpenType(openType);
        updatePo.setKeepalive(keepalive);
        updatePo.setSort(sort);
        updatePo.setUpdateDate(DateUtil.getTimestamp());

        baseMapper.updateNotNull(updatePo);
        return ResponseUtil.ok();
    }

    /**
     * 详情
     */
    @Override
    public GetMenuViewVo getView(MenuIdForm form) {
        Integer id = form.getId();
        Menu menu = menuMapper.findById(id);
        if (menu == null) {
            throw new ResponseException(ResponseCode.DATA);
        }

        // 查询上级
        String parentName = handleParentName(menu, "/");

        GetMenuViewVo vo = new GetMenuViewVo();
        BeanUtils.copyProperties(menu, vo);
        vo.setParentName(parentName);
        return vo;
    }

    /**
     * 处理上级菜单名称列表
     *
     * @param menu 菜单
     * @param sep  分隔符,默认/
     * @return 全部上级菜单名称
     */
    private String handleParentName(Menu menu, String sep) {
        Integer parentId = menu.getParentId();
        if (parentId == null || parentId <= 0) {
            return "";
        }
        String parentIds = menu.getParentIds();
        if (parentIds == null || parentIds.isEmpty()) {
            return "";
        }
        String[] ids = parentIds.split(",");
        List<Integer> idList = Arrays.stream(ids).map(Integer::valueOf).collect(Collectors.toList());


        List<Menu> menus = menuMapper.findList(idList);
        if (menus.isEmpty()) {
            return "";
        }

        if (sep == null || sep.isEmpty()) {
            sep = "/";
        }

        Map<Integer, String> nameMap = menus.stream().collect(Collectors.toMap(Menu::getId, Menu::getName));
        String[] names = new String[idList.size()];
        for (int i = 0; i < idList.size(); i++) {
            names[i] = nameMap.get(idList.get(i));
        }

        return String.join(sep, names);
    }
}
