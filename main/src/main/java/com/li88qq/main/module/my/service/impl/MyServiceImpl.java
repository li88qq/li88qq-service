package com.li88qq.main.module.my.service.impl;

import com.li88qq.bean.beans.system.Menu;
import com.li88qq.bean.beans.system.Role;
import com.li88qq.bean.beans.system.User;
import com.li88qq.main.dao.system.MenuMapper;
import com.li88qq.main.dao.system.RoleMapper;
import com.li88qq.main.dao.system.UserMapper;
import com.li88qq.main.module.my.dto.my.GetMyInfoVo;
import com.li88qq.main.module.my.dto.my.GetMyMenuListVo;
import com.li88qq.main.module.my.service.MyService;
import com.li88qq.publics.session.SessionUtil;
import com.li88qq.publics.session.UserToken;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author li88qq
 * @version 1.0 2023/12/31 15:49
 */
@Component
public class MyServiceImpl implements MyService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    /**
     * 获取我的基本信息
     */
    @Override
    public GetMyInfoVo getInfo() {
        int uid = SessionUtil.getUid();
        User user = userMapper.findById(uid);

        GetMyInfoVo vo = new GetMyInfoVo();
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setNickname(user.getNickname());
        vo.setAvatar(user.getAvatar());
        return vo;
    }

    /**
     * 获取我的菜单
     */
    @Override
    public List<GetMyMenuListVo> getMenuList() {
        UserToken userToken = SessionUtil.getUserToken();

        int roleId = userToken.getRoleId();
        List<Menu> menus = new ArrayList<>();
        if (roleId > 0) {
            Role role = roleMapper.findById(roleId);
            if (role != null) {
                Integer isAllMenu = role.getIsAllMenu();
                // 查询全部菜单
                if (isAllMenu != null && isAllMenu == 1) {
                    menus = menuMapper.findAll();
                } else { //查询授权菜单
                    menus = menuMapper.findByRoleId(roleId);
                }
            }
        }
        List<GetMyMenuListVo> voList = menus.stream().map(item -> {
            GetMyMenuListVo vo = new GetMyMenuListVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
        return voList;
    }
}
