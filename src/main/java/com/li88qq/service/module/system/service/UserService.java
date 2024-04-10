package com.li88qq.service.module.system.service;

import com.li88qq.db.dto.page.TPage;
import com.li88qq.service.module.system.dto.user.*;
import com.li88qq.service.config.web.response.BaseResponse;

/**
 * @author li88qq
 * @version 1.0 2023/12/29 23:03
 */
public interface UserService {

    BaseResponse save(SaveUserForm form);

    TPage<GetUserPageVo> getPage(GetUserPageForm form);

    BaseResponse delete(UserIdForm form);

    BaseResponse update(UpdateUserForm form);

    GetUserInfoVo getInfo(UserIdForm form);

    GetUserViewVo getView(UserIdForm form);
}
