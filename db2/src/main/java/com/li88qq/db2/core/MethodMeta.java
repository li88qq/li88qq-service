package com.li88qq.db2.core;

import com.li88qq.db2.annotion.Conditions;
import com.li88qq.db2.annotion.PageId;

/**
 * method元数据
 *
 * @author li88qq
 * @version 1.0 2022/3/12 22:24
 */
public class MethodMeta {

    private String className;
    private String methodName;
    private Conditions conditions;//条件语句
    private String pageable;//分页参数
    private PageId pageId;//分页注解
    private boolean queryPage;//是否分页查询

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    public String getPageable() {
        return pageable;
    }

    public void setPageable(String pageable) {
        this.pageable = pageable;
    }

    public PageId getPageId() {
        return pageId;
    }

    public void setPageId(PageId pageId) {
        this.pageId = pageId;
    }

    public boolean isQueryPage() {
        return queryPage;
    }

    public void setQueryPage(boolean queryPage) {
        this.queryPage = queryPage;
    }
}
