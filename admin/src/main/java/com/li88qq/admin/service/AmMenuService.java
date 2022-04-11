package com.li88qq.admin.service;

import com.li88qq.admin.dto.ammenu.*;
import com.li88qq.bean.web.response.BaseResponse;

import java.util.List;

/**
 * 菜单管理(后台)
 *
 * @author li88qq
 * @version 1.0 2022/4/10 22:42
 */
public interface AmMenuService {

    /**
     * 保存
     */
    BaseResponse save(SaveAmMenuForm form);

    /**
     * 更新
     */
    BaseResponse update(UpdateAmMenuForm form);

    /**
     * 查询菜单
     */
    AmMenuVo get(Integer id);

    /**
     * 查询菜单列表
     */
    List<AmMenuListVo> getList(AmMenuListForm form);

    /**
     * 查询菜单树
     */
    List<AmMenuTreeVo> getTree();
}
