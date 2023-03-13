package com.li88qq.admin.module.main.service;

import com.li88qq.admin.module.main.dto.role.*;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.db.dto.page.TPage;

/**
 * 角色管理
 *
 * @author li88qq
 * @version 1.0 2022/8/28 23:44
 */
public interface RoleService {

    /**
     * 保存
     */
    BaseResponse save(SaveRoleForm form);

    /**
     * 修改
     */
    BaseResponse update(UpdateRoleForm form);

    /**
     * 查询
     */
    RoleVo find(Integer id);

    /**
     * 分页查询
     */
    TPage<RolePageVo> findPage(RolePageForm form);

    /**
     * 删除
     */
    BaseResponse delete(Integer id);
}
