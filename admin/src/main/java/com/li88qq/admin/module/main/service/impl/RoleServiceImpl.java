package com.li88qq.admin.module.main.service.impl;

import com.li88qq.admin.dao.main.RoleMapper;
import com.li88qq.admin.module.main.dto.role.RolePageForm;
import com.li88qq.admin.module.main.dto.role.RolePageVo;
import com.li88qq.admin.module.main.dto.role.SaveRoleForm;
import com.li88qq.admin.module.main.dto.role.UpdateRoleForm;
import com.li88qq.admin.module.main.service.RoleService;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.db.core.BaseMapper;
import com.li88qq.db.dto.TPage;

import javax.annotation.Resource;

/**
 * 角色管理
 *
 * @author li88qq
 * @version 1.0 2022/8/28 23:49
 */
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
        return null;
    }

    /**
     * 修改
     */
    @Override
    public BaseResponse update(UpdateRoleForm form) {
        return null;
    }

    /**
     * 查询
     */
    @Override
    public BaseResponse find(Integer id) {
        return null;
    }

    /**
     * 分页查询
     */
    @Override
    public TPage<RolePageVo> findPage(RolePageForm form) {
        return null;
    }

    /**
     * 删除
     */
    @Override
    public BaseResponse delete(Integer id) {
        return null;
    }
}
