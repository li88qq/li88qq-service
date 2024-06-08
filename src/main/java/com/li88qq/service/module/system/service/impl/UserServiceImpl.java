package com.li88qq.service.module.system.service.impl;

import com.li88qq.service.bean.system.Role;
import com.li88qq.service.bean.system.User;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.dto.page.TPage;
import com.li88qq.db.utils.BeanUtil;
import com.li88qq.service.dao.BaseMapper;
import com.li88qq.service.dao.system.RoleMapper;
import com.li88qq.service.dao.system.UserMapper;
import com.li88qq.service.module.system.dto.user.*;
import com.li88qq.service.module.system.service.UserService;
import com.li88qq.service.utils.PasswordUtil;
import com.li88qq.service.config.web.response.BaseResponse;
import com.li88qq.service.config.web.response.ResponseCode;
import com.li88qq.service.config.web.response.ResponseException;
import com.li88qq.service.config.web.response.ResponseUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.beans.Transient;

/**
 * 用户管理
 *
 * @author li88qq
 * @version 1.0 2023/12/29 23:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private BaseMapper baseMapper;

    /**
     * 新增
     */
    @Override
    @Transient
    public BaseResponse save(SaveUserForm form) {
        Integer roleId = form.getRoleId();

        User user = new User();
        user.setUsername(form.getUsername());

        user.setNickname(form.getNickname());
        user.setMobile(form.getMobile());

        if (roleId != null && roleId > 0) {
            user.setRoleId(roleId);
        }

        // 处理密码
        String password = form.getPassword();
        if (password != null && !password.isEmpty()) {
            String hashPassword = PasswordUtil.getPassword(password);
            user.setPassword(hashPassword);
        }

        baseMapper.insertNoId(user);

        return ResponseUtil.ok();
    }

    /**
     * 分页查询
     */
    @Override
    public TPage<GetUserPageVo> getPage(GetUserPageForm form) {
        Pageable pageable = new Pageable(form.getPage(), form.getPageSize());
        Page<GetUserPageVo> pageVos = userMapper.findPage(form, pageable);
        return pageVos.build();
    }

    /**
     * 删除
     */
    @Override
    public BaseResponse delete(UserIdForm form) {
        User user = new User();
        user.setId(form.getId());
        baseMapper.delete(user);
        return ResponseUtil.ok();
    }

    /**
     * 编辑
     */
    @Override
    public BaseResponse update(UpdateUserForm form) {
        Integer id = form.getId();
        User user = userMapper.findById(id);
        if (user == null) {
            return ResponseUtil.error(ResponseCode.DATA);
        }

        Integer roleId = form.getRoleId();
        if (roleId == null || roleId < 0) {
            roleId = 0;
        }

        User updatePo = BeanUtil.reset(User.class);
        updatePo.setId(id);
        updatePo.setRoleId(roleId);

        // 处理密码
        String password = form.getPassword();
        if (password != null && !password.isEmpty()) {
            String hashPassword = PasswordUtil.getPassword(password);
            updatePo.setPassword(hashPassword);
        }

        updatePo.setMobile(form.getMobile());
        updatePo.setNickname(form.getNickname());

        baseMapper.updateNotNull(updatePo);

        return ResponseUtil.ok();
    }

    /**
     * 查询信息
     */
    @Override
    public GetUserInfoVo getInfo(UserIdForm form) {
        User user = userMapper.findById(form.getId());
        if (user == null) {
            throw new ResponseException(ResponseCode.DATA);
        }
        GetUserInfoVo vo = new GetUserInfoVo();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

    /**
     * 查询详情
     */
    @Override
    public GetUserViewVo getView(UserIdForm form) {
        User user = userMapper.findById(form.getId());
        if (user == null) {
            throw new ResponseException(ResponseCode.DATA);
        }
        GetUserViewVo vo = new GetUserViewVo();
        BeanUtils.copyProperties(user, vo);

        Integer roleId = user.getRoleId();
        String roleName = null;
        if (roleId != null && roleId > 0) {
            Role role = roleMapper.findById(roleId);
            if (role != null) {
                roleName = role.getName();
            }
        }
        vo.setRoleName(roleName);
        return vo;
    }
}
