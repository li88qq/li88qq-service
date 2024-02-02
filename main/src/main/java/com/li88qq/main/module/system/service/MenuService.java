package com.li88qq.main.module.system.service;

import com.li88qq.main.module.system.dto.menu.*;
import com.li88qq.publics.web.response.BaseResponse;

import java.util.List;

/**
 * 菜单
 *
 * @author li88qq
 * @version 1.0 2023/12/16 17:07
 */
public interface MenuService {

    /**
     * 新增
     */
    BaseResponse save(SaveMenuForm form);

    /**
     * 查询信息
     */
    GetMenuInfoVo getInfo(MenuIdForm form);

    /**
     * 分页查询
     */
    List<GetMenuPageVo> getPage(GetMenuPageForm form);

    /**
     * 查询列表
     */
    List<GetMenuListVo> getList();

    /**
     * 删除
     */
    BaseResponse delete(MenuIdForm form);

    /**
     * 编辑
     */
    BaseResponse update(UpdateMenuForm form);

    /**
     * 详情
     */
    GetMenuViewVo getView(MenuIdForm form);
}
