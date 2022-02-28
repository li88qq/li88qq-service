package com.li88qq.db.core;

import com.li88qq.db.annotations.*;
import com.li88qq.db.dto.QueryType;
import com.li88qq.db.dto.SqlMeta;
import com.li88qq.db.exception.QueryException;
import com.li88qq.db.utils.SqlUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sql处理器
 *
 * @author li88qq
 * @version 1.0 2022/2/26 23:47
 */
public class SqlHandler {

    //条件占位符,id > :id and name like :user.name
    private static final String PLACEHOLDER_ = ":[a-z]+(\\.[a-z]+)?";
    //where占位符
    private static final String PLACEHOLDER_WHERE = "#{where}";
    private static final String _PLACEHOLDER_ = "?";

    /**
     * 处理sql
     *
     * @param method 当前代理方法
     * @return sql对象
     */
    public static SqlMeta handle(Method method) {
        //流程
        //1.校验sql是否正确
        //2.参数处理
        //3.sql拼接

        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();


        //query注解
        Query query = method.getAnnotation(Query.class);
        if (query == null) {
            throw new QueryException("需要定义@Query注解");
        }
        String querySql = SqlUtil.trim(query.value());
        if (querySql.equals("")) {
            throw new QueryException("@Query注解value不能为空");
        }

        querySql = querySql.toLowerCase();
        //判断操作类型
        QueryType queryType = handleQueryType(querySql);
        if (queryType == null) {
            throw new QueryException("@Query注解中的sql语法错误,查询类型不知");
        }
        //搜索占位符 :xxx
        //搜索占位符 #{where}

        Modifying modifying = method.getAnnotation(Modifying.class);
        if (modifying == null) {
            if (queryType != QueryType.SELECT) {
                String msg = String.join("", "操作类型:[", queryType.name().toLowerCase(), "]需标明@Modifying注解");
                throw new QueryException(msg);
            }
        } else {
            if (queryType == QueryType.SELECT) {
                String msg = String.join("", "操作类型:[", queryType.name().toLowerCase(), "]不需要加@Modifying注解");
                throw new QueryException(msg);
            }
        }
        //条件sql
        StringBuilder whereSql = new StringBuilder();
        Conditions conditions = method.getAnnotation(Conditions.class);
        if (conditions != null) {
            Condition[] conditionsValue = conditions.value();
            if (conditionsValue == null || conditionsValue.length == 0) {
                throw new QueryException("@Conditions使用错误");
            }
            for (Condition condition : conditionsValue) {
                String conditionSql = SqlUtil.trim(condition.value());
                if (conditionSql.equals("")) {
                    throw new QueryException("@Condition value不能为空");
                }
                //搜索占位符 :xxx

                //判断是否需要添加该条件
            }
        }


        //参数处理
        //是否有分页参数
        List<Map<String, Class<?>>> paramClass = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        if (parameters != null && parameters.length > 0) {
            Map<String, Class<?>> paramMap = null;
            String paramName = null;
            for (Parameter parameter : parameters) {
                paramMap = new HashMap<>();
                Param param = parameter.getAnnotation(Param.class);
                paramName = SqlUtil.option(param.value(), parameter.getName());
                paramMap.put(paramName, parameter.getClass());
                paramClass.add(paramMap);
            }
        }

        return null;
    }

    /**
     * 校验方法
     *
     * @param method query方法
     */
    public static void checkMethod(Method method) {
        //1.参数校验
        //2.select
        //3.Modifying注解校验
        //4.占位符校验
    }

    private List<String> searchPlaceholder(String sql) {
        return null;
    }

    //判断sql查询类型
    private static QueryType handleQueryType(String sql) {
        for (QueryType type : QueryType.values()) {
            if (sql.startsWith(type.name().toLowerCase())) {
                return type;
            }
        }
        return null;
    }

}
