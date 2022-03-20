package com.li88qq.db2.core;

import com.li88qq.db2.annotion.Condition;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.*;
import org.apache.ibatis.session.Configuration;

import java.util.ArrayList;
import java.util.Collection;
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

    //private static final String PLACE_ = "#\\{[a-zA-Z]+(\\.[a-zA-Z]+)?\\}";//mybatis自带格式:#{param}
    private static final String PLACE_ = ":[a-zA-Z]+(\\.[a-zA-Z]+)?";//自定义格式: :param
    private static final String PLACE_WHERE = ":where";
    private static final String PLACE_MARK = "?";
    private static final String JOIN_MARK = " and ";

    public static Object invoke(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        MethodMeta methodMeta = new MethodMeta.Builder(mappedStatement.getId()).build();

        //处理动态条件语句
        handleCondition(metaObject, handler.getBoundSql(), methodMeta);

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

        String pageKey = pageable + ".pageNo";
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

    /**
     * 处理动态条件
     *
     * @param metaObject StatementHandler MetaObject
     * @param boundSql   BoundSql
     * @param methodMeta MethodMeta
     */
    private static void handleCondition(MetaObject metaObject, BoundSql boundSql, MethodMeta methodMeta) {
        //判断是否需要处理
        //1.没有注解
        Condition[] conditions = methodMeta.getConditions();
        if (conditions == null || conditions.length == 0) {
            return;
        }
        //2.没有参数
        if (boundSql.getParameterObject() == null) {
            return;
        }
        //3.没包含where占位符
        String sql = boundSql.getSql();
        if (!sql.contains(PLACE_WHERE)) {
            return;
        }

        //处理动态条件
        handleCondition(metaObject, boundSql, conditions);
    }

    /**
     * 动态拼接条件sql
     *
     * <p>
     * 关键点:
     * 1.mybatis已自动封装好了动态sql节点.关键类:SqlNode,SqlSource,DynamicSqlSource,
     * 2.动态封装好SqlSource后,需要重新获取sql,以及对应的参数,并调整metaObject
     * 3.metaObject.setValue("boundSql.sql", sql);
     * 4.metaObject.setValue("boundSql.parameterMappings", parameterMappings);
     * 5.metaObject.setValue("delegate.mappedStatement.sqlSource", sqlSource);
     * </p>
     *
     * @param metaObject StatementHandler MetaObject
     * @param boundSql   BoundSql
     * @param conditions Condition[]
     */
    private static void handleCondition(MetaObject metaObject, BoundSql boundSql, Condition[] conditions) {
        //sql分3部分
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");

        //拼接sqlSource
        List<SqlNode> sqlNodes = new ArrayList<>();

        //where之前
        String[] strings = sql.split(PLACE_WHERE);
        String whereBefore = strings[0];
        SqlNode whereBeforeNode = new StaticTextSqlNode(whereBefore);
        sqlNodes.add(whereBeforeNode);

        //where语句
        List<SqlNode> whereSqlNode = buildWhere(conditions, parameterObject, configuration);
        if (!whereSqlNode.isEmpty()) {
            SqlNode whereNodes = new MixedSqlNode(whereSqlNode);
            SqlNode whereRoot = new WhereSqlNode(configuration, whereNodes);
            sqlNodes.add(whereRoot);
        }

        //where之后
        if (strings.length == 2) {
            String where_after = strings[1];
            SqlNode sqlNode_after = new StaticTextSqlNode(where_after);
            sqlNodes.add(sqlNode_after);
        }

        //更新原对象
        SqlNode root = new MixedSqlNode(sqlNodes);
        SqlSource sqlSource = new DynamicSqlSource(configuration, root);
        BoundSql boundSqlUpdate = sqlSource.getBoundSql(parameterObject);
        List<ParameterMapping> parameterMappings = boundSqlUpdate.getParameterMappings();

        MetaObject boundSqlMeta = SystemMetaObject.forObject(boundSqlUpdate);
        Object parameters = boundSqlMeta.getValue("additionalParameters");
        Object metaParameters = boundSqlMeta.getValue("metaParameters");

        metaObject.setValue("boundSql.sql", boundSqlUpdate.getSql());
        metaObject.setValue("boundSql.metaParameters", metaParameters);
        metaObject.setValue("boundSql.parameterMappings", parameterMappings);
        metaObject.setValue("boundSql.additionalParameters", parameters);

        metaObject.setValue("delegate.mappedStatement.sqlSource", sqlSource);
    }

    /**
     * 动态构建where节点
     *
     * @param conditions      条件注解列表
     * @param parameterObject 参数对象
     * @param configuration   配置
     * @return where节点
     */
    private static List<SqlNode> buildWhere(Condition[] conditions, Object parameterObject, Configuration configuration) {
        List<SqlNode> sqlNodes = new ArrayList<>();
        String sql; //条件sql语句
        Object paramValue;//参数值
        Pattern pattern = Pattern.compile(PLACE_);
        Matcher matcher;
        boolean addFlag;//当前条件是否成立,如果有多个,一个成立, 整条语句都成立
        boolean staticFlag;//当前是否存在条件
        String param;//:param
        String param_1;//param去掉:后

        //参数map
        MetaObject paramMap = SystemMetaObject.forObject(parameterObject);
        SqlNode sqlNode = null;

        //语句判断
        for (Condition condition : conditions) {
            addFlag = false;
            staticFlag = true;
            param_1 = "";
            paramValue = null;

            sql = condition.value();
            //找到对应的key
            matcher = pattern.matcher(sql);

            //判断当前sql是否添加
            while (matcher.find()) {
                staticFlag = false;

                param = matcher.group();
                param_1 = param.substring(1);
                paramValue = paramMap.getValue(param_1);
                if (paramValue == null) {
                    continue;
                }
                addFlag = true;
            }

            if (staticFlag) {
                sqlNode = new StaticTextSqlNode(sql);
                sqlNodes.add(sqlNode);
            }
            if (addFlag) {
                buildNode(sqlNodes, sql, param_1, paramValue, configuration);
            }
        }

        return sqlNodes;
    }

    //动态构建条件sqlNode
    private static void buildNode(List<SqlNode> sqlNodes, String sql, String param, Object value, Configuration configuration) {
        SqlNode sqlNode = null;
        if (value instanceof Collection<?> || value.getClass().isArray()) {
            String sqlBefore = sql.split(":" + param)[0];
            String node_before = String.join("", JOIN_MARK, sqlBefore);
            sqlNodes.add(new StaticTextSqlNode(node_before));

            List<SqlNode> nodes = new ArrayList<>();
            nodes.add(new StaticTextSqlNode("#{item}"));
            SqlNode content = new MixedSqlNode(nodes);
            sqlNode = new ForEachSqlNode(configuration, content,
                    param, "index", "item", "(", ")", ",");
        } else {
            sql = sql.replace(":" + param, String.format("#{%s}", param));
            sqlNode = new TextSqlNode(String.join("", JOIN_MARK, sql));
        }
        sqlNodes.add(sqlNode);
    }

    //旧逻辑
    @Deprecated
    private static void handleCondition2(Condition[] conditions, MetaObject metaObject) {
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
