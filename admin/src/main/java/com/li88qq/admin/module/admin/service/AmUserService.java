package com.li88qq.admin.module.admin.service;

import com.li88qq.admin.module.admin.dto.amuser.AddAmUserForm;
import com.li88qq.admin.module.admin.dto.amuser.AmUserInfo;
import com.li88qq.admin.module.admin.dto.amuser.AmUserPageForm;
import com.li88qq.admin.module.admin.dto.amuser.AmUserPageVo;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.db.dto.TPage;

/**
 * 用户(后台)
 *
 * @author li88qq
 * @version 1.0 2022/3/28 17:51
 */
public interface AmUserService {

    /**
     * 查询用户(后台)信息
     */
    AmUserInfo getInfo();

    /**
     * 分页查询用户(后台)信息
     */
    TPage<AmUserPageVo> getPage(AmUserPageForm form);

    /**
     * 添加用户(后台)
     */
    BaseResponse add(AddAmUserForm form);
}
