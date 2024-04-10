package com.li88qq.service.module.system.service;

import com.li88qq.db.dto.page.TPage;
import com.li88qq.service.module.system.dto.role.*;
import com.li88qq.service.dto.form.IdForm;
import com.li88qq.service.config.web.response.BaseResponse;

import java.util.List;

/**
 * 角色
 *
 * @author li88qq
 * @version 1.0 2024/1/1 16:49
 */
public interface RoleService {

    /**
     * 查询列表
     */
    List<GetRoleListVo> getList();

    /**
     * 新增
     */
    BaseResponse save(SaveRoleForm form);

    /**
     * 分页查询
     */
    TPage<GetRolePageVo> getPage(GetRolePageForm form);

    /**
     * 删除
     */
    BaseResponse delete(IdForm form);

    /**
     * 编辑
     */
    BaseResponse update(UpdateRoleForm form);

    /**
     * 查询信息
     */
    GetRoleInfoVo getInfo(IdForm form);

    /**
     * 查询详情
     */
    GetRoleViewVo getView(IdForm form);

    /**
     * 授权菜单
     */
    BaseResponse authMenu(AuthMenuForm form);

    /**
     * 查询授权菜单
     */
    GetAuthMenusVo getAuthMenus(IdForm form);
}
