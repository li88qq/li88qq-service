package com.li88qq.admin.module.main.service.impl;

import com.li88qq.admin.dao.main.RoleMapper;
import com.li88qq.admin.module.main.dto.role.*;
import com.li88qq.admin.module.main.service.RoleService;
import com.li88qq.bean.entity.system.Role;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.bean.web.response.ResponseUtil;
import com.li88qq.db.core.BaseMapper;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
import com.li88qq.db.dto.TPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色管理
 *
 * @author li88qq
 * @version 1.0 2022/8/28 23:49
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private BaseMapper baseMapper;
    @Resource
    private RoleMapper roleMapper;

    /**
     * 保存
     */
    @Override
    public BaseResponse save(SaveRoleForm form) {
        Role role = new Role();
        role.setName(form.getName());
        role.setRemark(form.getRemark());
        baseMapper.save(role);
        return ResponseUtil.ok();
    }

    /**
     * 修改
     */
    @Override
    public BaseResponse update(UpdateRoleForm form) {
        Role role = BaseMapper.reset(Role.class);
        role.setId(form.getId());
        role.setName(form.getName());
        role.setRemark(form.getRemark());

        baseMapper.updateNoNull(role);
        return ResponseUtil.ok();
    }

    /**
     * 查询
     */
    @Override
    public RoleVo find(Integer id) {
        Role role = roleMapper.findById(id);
        RoleVo vo = new RoleVo();
        vo.setName(role.getName());
        vo.setRemark(role.getRemark());
        return vo;
    }

    /**
     * 分页查询
     */
    @Override
    public TPage<RolePageVo> findPage(RolePageForm form) {
        Pageable pageable = new Pageable(form.getPage(), form.getPageSize());
        Page<RolePageVo> page = roleMapper.findPage(form, pageable);
        return page.build();
    }

    /**
     * 删除
     */
    @Override
    public BaseResponse delete(Integer id) {
        roleMapper.deleteById(id);
        return ResponseUtil.ok();
    }
}
