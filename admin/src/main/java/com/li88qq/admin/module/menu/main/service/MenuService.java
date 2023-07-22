package com.li88qq.admin.module.menu.main.service;

import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageForm;
import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageVo;
import com.li88qq.admin.module.menu.main.dto.menu.SaveMenuForm;
import com.li88qq.common.web.response.BaseResponse;
import com.li88qq.db.dto.page.TPage;

/**
 * 菜单
 *
 * @author li88qq
 * @version 1.0 2023/7/19 22:25
 */
public interface MenuService {

    /**
     * 新增
     */
    BaseResponse save(SaveMenuForm form);

    /**
     * 分页查询菜单
     */
    TPage<GetMenuPageVo> page(GetMenuPageForm form);
}
