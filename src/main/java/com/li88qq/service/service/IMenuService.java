package com.li88qq.service.service;

import com.li88qq.service.response.MenuVo;

import java.util.List;

/**
 * 菜单服务
 *
 * @author li88qq
 * @version 1.0 2021/10/20 23:31
 */
public interface IMenuService {

    /**
     * 获取菜单列表
     */
    List<MenuVo> getList();
}
