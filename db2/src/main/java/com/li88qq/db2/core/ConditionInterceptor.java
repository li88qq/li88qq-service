package com.li88qq.db2.core;

import com.li88qq.db2.annotion.Condition;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 动态条件处理
 *
 * @author li88qq
 * @version 1.0 2022/3/12 22:48
 */
class ConditionInterceptor {

    private static final String PLACE_ = ":[a-z]+(\\.[a-z]+)?";
    private static final String PLACE_WHERE = ":where";
    private static final String PLACE_MARK = "?";
    private static final String JOIN_MARK = " and ";

    public static Object invoke(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        MethodMeta methodMeta = new MethodMeta.Builder(mappedStatement.getId()).build();
        //判断是否需要处理,需要@Condition及原语句包含:where
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();
        if (methodMeta.getConditions() == null || !sql.contains(PLACE_WHERE)) {
            return invocation.proceed();
        }

        //处理动态条件
        Condition[] conditions = methodMeta.getConditions();
        if (conditions != null) {
            handleCondition(conditions, metaObject);
        }

        //处理分页条件
        String pageable = methodMeta.getPageable();
        if (pageable != null) {
            handlePageLimit(pageable, metaObject);
        }

        return invocation.proceed();
    }

    private static void handlePageLimit(String pageable, MetaObject metaObject) {
        BoundSql boundSql = (BoundSql) metaObject.getValue("boundSql");
        String sql = boundSql.getSql();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        String pageKey = pageable + ".page";
        String pageSizeKey = pageable + ".pageSize";

        Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");
        ParameterMapping pageMapping = new ParameterMapping.Builder(configuration, pageKey, Object.class).build();
        ParameterMapping pageSizeMapping = new ParameterMapping.Builder(configuration, pageSizeKey, Object.class).build();
        parameterMappings.add(pageMapping);
        parameterMappings.add(pageSizeMapping);

        sql += " limit ?,?";
        metaObject.setValue("boundSql.sql", sql);
        metaObject.setValue("boundSql.parameterMappings", parameterMappings);
    }

    private static void handleCondition(Condition[] conditions, MetaObject metaObject) {
//        BoundSql boundSql = (BoundSql) metaObject.getValue("boundSql");
//        MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>) boundSql.getParameterObject();
//
//        StringBuilder whereSql = new StringBuilder();
//        String value;
//        Pattern pattern = Pattern.compile(PLACE_);
//        Matcher matcher = null;
//        String paramKey = null;
//        Object paramValue = null;
//        boolean addFlag = false; //是否加入sql,如果一个condition里面有多个, 只要有一个成立, 就全部成立
//        List<String> whereList = new ArrayList<>();
//        for (Condition condition : conditions) {
//            value = condition.value();
//            if (value == null || value.equals("")) {
//                continue;
//            }
//            //搜索 :xxx 占位符,有替换,无则直接加
//            matcher = pattern.matcher(value);
//            addFlag = false;
//            while (matcher.find()) {
//                paramKey = matcher.group();
//                paramKey = paramKey.replace(":", "");
//                paramValue = paramMap.get(paramKey);
//                if (paramValue == null) {
//                    continue;
//                }
//                addFlag = true;
//            }
//            if (!addFlag) {
//                continue;
//            }
//            whereList.add(value);
//        }
//        if (whereList.isEmpty()) {
//            return;
//        }
//
//        String sql = boundSql.getSql();
//        Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");
//
//        whereSql.append(" where ");
//        whereSql.append(whereList.get(0));
//        for (int i = 1; i < whereList.size(); i++) {
//            whereSql.append(JOIN_MARK).append(whereList.get(i));
//        }
//
//        List<ParameterMapping> parameterMappings = new ArrayList<>();
//        Matcher queryMatcher = pattern.matcher(whereSql);
//        while (queryMatcher.find()) {
//            String key = queryMatcher.group().replace(":", "");
//            ParameterMapping mapping = new ParameterMapping.Builder(configuration, key, Object.class).build();
//            parameterMappings.add(mapping);
//        }
//        String whereSql2 = whereSql.toString().replaceAll(PLACE_, PLACE_MARK);
//        String newSql = sql.replace(PLACE_WHERE, whereSql2);
//
//        metaObject.setValue("boundSql.sql", newSql);
//        metaObject.setValue("boundSql.parameterMappings", parameterMappings);
    }
}
