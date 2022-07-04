package com.li88qq.db.core;

import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分页返回处理
 *
 * @author li88qq
 * @version 1.0 2022/3/12 22:49
 */
class PageInterceptor {

    /**
     * 封装分页对象
     */
    public static Object invoke(Invocation invocation) throws Throwable {
        ResultSetHandler handler = (ResultSetHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("mappedStatement");
        MethodMeta methodMeta = null;
        // 这里有可能mybatis自己调用接口,比如insert语句返回id
        try {
            methodMeta = new MethodMeta.Builder(mappedStatement.getId()).build();
        } catch (Exception e) {
            return invocation.proceed();
        }
        Object proceed = invocation.proceed();
        //不是分页查询,不处理
        if (!(methodMeta.isQueryPage())) {
            return proceed;
        }

        //构建查询count语句
        Configuration configuration = (Configuration) metaObject.getValue("configuration");
        BoundSql boundSql = (BoundSql) metaObject.getValue("boundSql");
        BoundSql newBoundSql = buildNewBoundSql(configuration, boundSql, methodMeta.getPageId());
        long count = queryCount(configuration, newBoundSql);

        //获取pageable
        Object parameterObject = boundSql.getParameterObject();
        Pageable pageable = null;
        if (parameterObject instanceof Pageable pageable1) {
            pageable = pageable1;
        } else {
            MapperMethod.ParamMap<?> _parameterObject = (MapperMethod.ParamMap<?>) parameterObject;
            pageable = (Pageable) _parameterObject.get(methodMeta.getPageable());
        }

        //封装page对象
        ArrayList<?> list = (ArrayList<?>) proceed;
        Page<?> page = Page.convert(list);
        page.setTotal(count);
        page.setPage(pageable.getPage());
        page.setPageSize(pageable.getPageSize());

        return page;
    }

    /**
     * 构建统计语句
     *
     * @param configuration 配置
     * @param boundSql      原来的
     * @param pageId        分页id
     * @return 统计语句
     */
    private static BoundSql buildNewBoundSql(Configuration configuration, BoundSql boundSql, PageId pageId) {
        String sql = boundSql.getSql();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Object parameterObject = boundSql.getParameterObject();

        //处理统计字段
        String countField = "id";
        if (pageId != null) {
            String countField1 = pageId.countField();
            if (countField1 != null && !countField1.equals("")) {
                countField = countField1;
            }
        }

        //如果有limit,判断是否有?
        int limitIndex = sql.lastIndexOf("limit");
        if (limitIndex != -1) {
            String limitSql = sql.substring(limitIndex);
            Pattern pattern = Pattern.compile("\\?");
            Matcher matcher = pattern.matcher(limitSql);
            int count = 0;
            while (matcher.find()) {
                count++;
            }
            for (int i = 0; i < count; i++) {
                parameterMappings.remove(parameterMappings.size() - 1);
            }
            sql = sql.substring(0, limitIndex);
        }

        StringBuilder sqlBuilder = new StringBuilder();
        //第一个from
        int fromIndex = sql.indexOf("from");
        sqlBuilder.append("select").append(" count(").append(countField).append(") ").append(sql.substring(fromIndex));
        BoundSql newBoundSql = new BoundSql(configuration, sqlBuilder.toString(), parameterMappings, parameterObject);
        MetaObject metaObject = SystemMetaObject.forObject(newBoundSql);
        MetaObject metaObjectOld = SystemMetaObject.forObject(boundSql);

        metaObject.setValue("metaParameters", metaObjectOld.getValue("metaParameters"));
        metaObject.setValue("additionalParameters", metaObjectOld.getValue("additionalParameters"));
        return newBoundSql;
    }

    /**
     * 执行查询语句
     *
     * @param configuration 配置
     * @param boundSql      查询
     * @return 统计数量
     */
    private static long queryCount(Configuration configuration, BoundSql boundSql) {
        Connection connection = null;
        long count = 0L;
        try {
            connection = configuration.getEnvironment().getDataSource().getConnection();
            String sql = boundSql.getSql();
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            PreparedStatement statement = connection.prepareStatement(sql);
            Object parameterObject = boundSql.getParameterObject();
            MetaObject paramMetaObject = SystemMetaObject.forObject(parameterObject);

            String key;
            Object value;
            for (int i = 0; i < parameterMappings.size(); i++) {
                key = parameterMappings.get(i).getProperty();
                if (!key.startsWith(ForEachSqlNode.ITEM_PREFIX)) {
                    value = paramMetaObject.getValue(key);
                } else {
                    value = boundSql.getAdditionalParameter(key);
                }
                statement.setObject(i + 1, value);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getLong(1);
            }
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
