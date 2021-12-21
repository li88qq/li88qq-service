package com.li88qq.db.dto;

import java.lang.reflect.Method;

/**
 * 调用方法分析对象
 *
 * @author li88qq
 * @version 1.0 2021/12/20 23:48
 */
public class MethodDto {

    private Class<?> clazz;//调用方法类
    private Method method;//方法方法
    private Pageable pageable;//分页对象

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
