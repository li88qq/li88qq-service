package com.li88qq.admin.module.system.admin.service;

import com.li88qq.admin.module.system.admin.dto.config.*;
import com.li88qq.common.dto.IdIntForm;
import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.db.dto.page.TPage;

/**
 * 应用设置
 *
 * @author li88qq
 * @version 1.0 2023/7/23 16:57
 */
public interface AppConfigService {

    /**
     * 新增
     */
    BaseResponse save(SaveForm form);

    /**
     * 编辑
     */
    BaseResponse update(UpdateForm form);

    /**
     * 删除
     */
    BaseResponse delete(IdIntForm form);

    /**
     * 分页查询
     */
    TPage<GetPageVo> page(GetPageForm form);

    /**
     * 查询
     */
    GetVo get(IdIntForm form);

    /**
     * 详情
     */
    GetViewVo view(IdIntForm form);

    /**
     * 批量设置
     */
    BaseResponse batchUpdate(BatchUpdateForm form);
}
