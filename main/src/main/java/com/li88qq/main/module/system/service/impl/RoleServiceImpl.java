package com.li88qq.main.module.system.service.impl;

import com.li88qq.bean.beans.system.Role;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.dto.page.TPage;
import com.li88qq.db.utils.BeanUtil;
import com.li88qq.main.dao.BaseMapper;
import com.li88qq.main.dao.system.RoleMapper;
import com.li88qq.main.dao.system.Role_MenuMapper;
import com.li88qq.main.module.system.dto.role.*;
import com.li88qq.main.module.system.service.RoleService;
import com.li88qq.publics.form.IdForm;
import com.li88qq.publics.web.response.BaseResponse;
import com.li88qq.publics.web.response.ResponseCode;
import com.li88qq.publics.web.response.ResponseException;
import com.li88qq.publics.web.response.ResponseUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author li88qq
 * @version 1.0 2024/1/1 16:50
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private Role_MenuMapper role_menuMapper;
    @Resource
    private BaseMapper baseMapper;

    @Override
    public List<GetRoleListVo> getList() {
        List<Role> roleList = roleMapper.findList();

        return roleList.stream().map(item -> {
            GetRoleListVo vo = new GetRoleListVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public BaseResponse save(SaveRoleForm form) {
        Role role = new Role();
        role.setName(form.getName());


        baseMapper.insertNoId(role);

        return ResponseUtil.ok();
    }

    @Override
    public TPage<GetRolePageVo> getPage(GetRolePageForm form) {
        Pageable pageable = new Pageable(form.getPage(),form.getPageSize());
        Page<GetRolePageVo> pageVos =  roleMapper.getPage(form,pageable);


        return pageVos.build();
    }

    @Override
    public BaseResponse delete(IdForm form) {
        Role role = new Role();
        role.setId(form.getId());
        baseMapper.delete(role);
        return ResponseUtil.ok();
    }

    @Override
    public BaseResponse update(UpdateRoleForm form) {
        Role role = roleMapper.findById(form.getId());
        if (role == null) {
            return ResponseUtil.error(ResponseCode.DATA);
        }

        Role updatePo = BeanUtil.reset(Role.class);
        updatePo.setId(form.getId());
        updatePo.setName(form.getName());

        baseMapper.updateNotNull(updatePo);

        return ResponseUtil.ok();
    }

    @Override
    public GetRoleInfoVo getInfo(IdForm form) {
        Role role = roleMapper.findById(form.getId());
        if (role == null) {
            throw new ResponseException((ResponseCode.DATA));
        }
        GetRoleInfoVo vo = new GetRoleInfoVo();
        BeanUtils.copyProperties(role, vo);
        return vo;
    }

    @Override
    public GetRoleViewVo getView(IdForm form) {
        Role role = roleMapper.findById(form.getId());
        if (role == null) {
            throw new ResponseException((ResponseCode.DATA));
        }
        GetRoleViewVo vo = new GetRoleViewVo();
        BeanUtils.copyProperties(role, vo);
        return vo;
    }

    /**
     * 授权菜单
     */
    @Override
    public BaseResponse authMenu(AuthMenuForm form) {
        Integer roleId = form.getRoleId();
        Integer isAllMenu = form.getIsAllMenu();
        List<Integer> menuIds = form.getMenuIds();
        // 查询角色是否存在
        Role role = roleMapper.findById(roleId);
        if (role == null) {
            return ResponseUtil.error(ResponseCode.DATA);
        }

        // 先删除全部
        role_menuMapper.deleteByRole(roleId);

        //是否授权全部
        if (isAllMenu == null || isAllMenu != 1) {
            isAllMenu = 0;
        }
        if (!isAllMenu.equals(role.getIsAllMenu())) {
            roleMapper.updateIsAllMenu(roleId, isAllMenu);
        }
        if (isAllMenu == 1) {
            return ResponseUtil.ok();
        }

        if (menuIds != null && !menuIds.isEmpty()) {
            role_menuMapper.authByMenuIds(roleId, menuIds);
        }
        return ResponseUtil.ok();
    }

    /**
     * 查询授权菜单
     */
    @Override
    public GetAuthMenusVo getAuthMenus(IdForm form) {
        Integer id = form.getId();
        // 查询角色是否存在
        Role role = roleMapper.findById(id);
        if (role == null) {
            throw new ResponseException(ResponseCode.DATA);
        }
        Integer isAllMenu = role.getIsAllMenu();
        List<Integer> menuIds = null;
        if (isAllMenu != null && isAllMenu == 0) {
            menuIds = role_menuMapper.findByRoleId(id);
        }

        GetAuthMenusVo vo = new GetAuthMenusVo();
        vo.setIsAllMenu(isAllMenu);
        vo.setMenuIds(menuIds);
        return vo;
    }
}
