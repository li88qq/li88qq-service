package com.li88qq.service.module.system.service;

import com.li88qq.db.dto.page.TPage;
import com.li88qq.service.module.system.dto.user.*;
import com.li88qq.service.config.web.response.BaseResponse;

/**
 * 用户管理
 *
 * @author li88qq
 * @version 1.0 2023/12/29 23:03
 */
public interface UserService {

    /**
     * 新增
     */
    BaseResponse save(SaveUserForm form);

    /**
     * 分页查询
     */
    TPage<GetUserPageVo> getPage(GetUserPageForm form);

    /**
     * 删除
     */
    BaseResponse delete(UserIdForm form);

    /**
     * 编辑
     */
    BaseResponse update(UpdateUserForm form);

    /**
     * 查询信息
     */
    GetUserInfoVo getInfo(UserIdForm form);

    /**
     * 查询详情
     */
    GetUserViewVo getView(UserIdForm form);
}
