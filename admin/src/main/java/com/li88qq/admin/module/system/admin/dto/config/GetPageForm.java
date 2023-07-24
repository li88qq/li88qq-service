package com.li88qq.admin.module.system.admin.dto.config;

import com.li88qq.db.dto.page.PageForm;

/**
 * 分页查询
 *
 * @author li88qq
 * @version 1.0 2023/7/23 17:08
 */
public class GetPageForm extends PageForm {

    private String domain;//域名
    private String appName;//应用名称

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
