package com.li88qq.db.interceptor.chains;

import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.Pageable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * PageId声明分页处理
 *
 * @author li88qq
 * @version 1.0 2023/3/1 22:03
 */
@Component
public class PageIdChain implements InterceptorChain {

    @Override
    public boolean execute(StatementHandler handler, Method method) {
        //是否有PageId
        PageId pageId = method.getDeclaredAnnotation(PageId.class);
        if (pageId == null) {
            return true;
        }
        //Pageable对应的参数
        String pageableParam = getPageable(method);
        if (pageableParam == null) {
            return true;
        }

        //加入limit
        addLimitSql(handler, pageableParam);
        return true;
    }

    /**
     * 加入limit
     *
     * @param handler       StatementHandler
     * @param pageableParam Pageable
     */
    private void addLimitSql(StatementHandler handler, String pageableParam) {
        MetaObject metaObject = SystemMetaObject.forObject(handler);

        BoundSql boundSql = (BoundSql) metaObject.getValue("boundSql");
        String sql = boundSql.getSql();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //这里,如果不new,是不允许add操作的
        if (parameterMappings.isEmpty()) {
            parameterMappings = new ArrayList<>();
        }
        String pageKey = null;
        String pageSizeKey = null;
        Object parameterObject = boundSql.getParameterObject();
        //如果是仅有一个参数对象
        if (parameterObject instanceof Pageable) {
            pageKey = "pageNo";
            pageSizeKey = "pageSize";
        } else {
            pageKey = pageableParam + ".pageNo";
            pageSizeKey = pageableParam + ".pageSize";
        }
        Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");
        ParameterMapping pageMapping = new ParameterMapping.Builder(configuration, pageKey, Integer.class).build();
        ParameterMapping pageSizeMapping = new ParameterMapping.Builder(configuration, pageSizeKey, Integer.class).build();
        parameterMappings.add(pageMapping);
        parameterMappings.add(pageSizeMapping);

        sql += " limit ?,?";
        metaObject.setValue("boundSql.sql", sql);
        metaObject.setValue("boundSql.parameterMappings", parameterMappings);
    }

    /**
     * 获取Pageable对应数据
     *
     * @param method 方法
     * @return pageable
     */
    private String getPageable(Method method) {
        Parameter[] parameters = method.getParameters();
        String pageable = null;
        for (Parameter parameter : parameters) {
            Class<?> type = parameter.getType();
            if (type == Pageable.class) {
                Param param = parameter.getDeclaredAnnotation(Param.class);
                if (param != null) {
                    //注意:如果值为空字符串,mybatis也是允许的
                    pageable = param.value();
                } else {
                    pageable = parameter.getName();
                }
                break;
            }
        }

        return pageable;
    }

}
