package com.li88qq.admin.module.admin.service.impl;

import com.li88qq.admin.dao.admin.AmMenuMapper;
import com.li88qq.admin.module.admin.dto.ammenu.*;
import com.li88qq.admin.module.admin.service.AmMenuService;
import com.li88qq.bean.dto.IdsForm;
import com.li88qq.bean.entity.am.system.AmMenu;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.bean.web.response.ResponseUtil;
import com.li88qq.db.core.BaseMapper;
import com.li88qq.utils.DateUtil;
import com.li88qq.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单管理(后台)
 *
 * @author li88qq
 * @version 1.0 2022/4/10 22:59
 */
@Service
public class AmMenuServiceImpl implements AmMenuService {

    @Resource
    private BaseMapper baseMapper;
    @Resource
    private AmMenuMapper amMenuMapper;

    /**
     * 保存
     */
    @Override
    public BaseResponse save(SaveAmMenuForm form) {
        AmMenu amMenu = new AmMenu();
        Integer parentId = form.getParentId();
        if (parentId == null || parentId < 0) {
            parentId = 0;
        }
        Integer sort = form.getSort();
        if (sort == null || sort < 0) {
            sort = 0;
        }

        amMenu.setName(form.getName());
        amMenu.setParentId(parentId);
        amMenu.setUrl(form.getUrl());
        amMenu.setRouter(form.getRouter());
        amMenu.setSort(sort);
        amMenu.setIcon(form.getIcon());

        baseMapper.save(amMenu);
        return ResponseUtil.ok();
    }

    /**
     * 修改
     */
    @Override
    public BaseResponse update(UpdateAmMenuForm form) {
        Integer id = form.getId();
        AmMenu amMenu = amMenuMapper.findById(id);
        if (amMenu == null) {
            return ResponseUtil.error("记录不存在");
        }
        Integer parentId = form.getParentId();
        if (parentId == null || parentId < 0) {
            parentId = 0;
        }
        Integer sort = form.getSort();
        if (sort == null || sort < 0) {
            sort = 0;
        }

        amMenu.setName(form.getName());
        amMenu.setParentId(parentId);
        amMenu.setUrl(form.getUrl());
        amMenu.setRouter(form.getRouter());
        amMenu.setSort(sort);
        amMenu.setIcon(form.getIcon());
        amMenu.setUpdateDate(DateUtil.getTimestamp());

        baseMapper.update(amMenu);
        return ResponseUtil.ok();
    }

    /**
     * 查询
     */
    @Override
    public AmMenuVo get(Integer id) {
        if (id == null || id <= 0) {
            throw ResponseUtil.exception("参数错误");
        }
        AmMenu amMenu = amMenuMapper.findById(id);
        if (amMenu == null) {
            throw ResponseUtil.exception("记录不存在");
        }
        AmMenuVo vo = new AmMenuVo();
        vo.setParentId(amMenu.getParentId());
        vo.setName(amMenu.getName());
        vo.setIcon(amMenu.getIcon());
        vo.setUrl(amMenu.getUrl());
        vo.setRouter(amMenu.getRouter());
        vo.setSort(amMenu.getSort());
        return vo;
    }

    /**
     * 查询列表
     */
    @Override
    public List<AmMenuListVo> getList(AmMenuListForm form) {
        return amMenuMapper.findList(form);
    }

    /**
     * 查询树状结构
     */
    @Override
    public List<AmMenuTreeVo> getTree() {
        return amMenuMapper.findTree();
    }

    /**
     * 删除
     */
    @Override
    public BaseResponse delete(IdsForm form) {
        String ids = form.getIds();
        List<Long> idList = StringUtil.fromIds(ids);
        if (idList.isEmpty()) {
            return ResponseUtil.error("参数错误");
        }
        amMenuMapper.deleteByIds(idList);
        return ResponseUtil.ok();
    }
}
