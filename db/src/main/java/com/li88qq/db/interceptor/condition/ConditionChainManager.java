package com.li88qq.db.interceptor.condition;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.dto.SqlConst;
import com.li88qq.db.dto.sql.NodeDto;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.*;
import org.apache.ibatis.session.Configuration;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 条件职责链控制器
 *
 * @author li88qq
 * @version 1.0 2023/3/8 23:39
 */
public class ConditionChainManager {

    private final StatementHandler handler;
    private final Condition[] conditions;
    private final LinkedList<NodeChain> chains = new LinkedList<>();
    private final List<SqlNode> conditionNodes = new ArrayList<>();
    private final Set<String> formatSet = new HashSet<>();

    public ConditionChainManager(StatementHandler handler, Condition[] conditions) {
        this.handler = handler;
        this.conditions = conditions;
    }

    /**
     * 添加拦截链
     *
     * @param chain 链
     */
    public void add(NodeChain chain) {
        chains.add(chain);
    }

    /**
     * 执行
     */
    public void execute() {
        for (Condition condition : conditions) {
            NodeDto nodeDto = init(condition);

            //是否静态
            if (nodeDto == null) {
                buildStaticTextSqlNode(condition);
                continue;
            }

            //null值
            Object value = nodeDto.getValue();
            if (value == null) {
                continue;
            }

            //职责链处理
            for (NodeChain chain : chains) {
                boolean check = chain.check(value.getClass());
                //如果该节点有处理,就退出当前字段职责链
                if (check) {
                    chain.handle(this, condition, nodeDto);
                    handleEndNode(condition, nodeDto);
                    break;
                }
            }
        }

        updateBoundSql();
    }

    /**
     * 初始化
     *
     * @param condition 条件
     * @return NodeDto
     */
    public NodeDto init(Condition condition) {
        NodeDto nodeDto = null;
        String value = condition.value();
        Pattern pattern = Pattern.compile(SqlConst.PLACE_);
        Matcher matcher = pattern.matcher(value);
        BoundSql boundSql = handler.getBoundSql();
        Object parameterObject = boundSql.getParameterObject();
        MetaObject metaObject = SystemMetaObject.forObject(parameterObject);

        if (matcher.find()) {
            nodeDto = new NodeDto();
            String group = matcher.group();
            String key = group.substring(1);
            Object nodeValue = metaObject.getValue(key);

            nodeDto.setKey(key);
            nodeDto.setValue(nodeValue);
        }
        return nodeDto;
    }

    /**
     * 最后拼接BoundSql
     */
    public void updateBoundSql() {
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        BoundSql boundSql = (BoundSql) metaObject.getValue("boundSql");

        //sql分3部分
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");

        //拼接sqlSource
        List<SqlNode> sqlNodes = new ArrayList<>();

        //where之前
        String[] strings = sql.split(SqlConst.PLACE_WHERE);
        String where_before = strings[0];
        SqlNode sqlNode_before = new StaticTextSqlNode(where_before);
        sqlNodes.add(sqlNode_before);

        //where语句
        if (!conditionNodes.isEmpty()) {
            SqlNode whereNodes = new MixedSqlNode(conditionNodes);
            SqlNode whereRoot = new WhereSqlNode(configuration, whereNodes);
            sqlNodes.add(whereRoot);
        }

        List<ParameterMapping> afterParameterMapping = null;
        //where之后
        if (strings.length == 2) {
            String where_after = strings[1];
            SqlNode sqlNode_after = new StaticTextSqlNode(where_after);
            sqlNodes.add(sqlNode_after);

            afterParameterMapping = buildAfterParameterMapping(where_after, boundSql);
        }

        //更新原对象
        SqlNode root = new MixedSqlNode(sqlNodes);
        SqlSource sqlSource = new DynamicSqlSource(configuration, root);
        BoundSql boundSqlUpdate = sqlSource.getBoundSql(parameterObject);
        List<ParameterMapping> parameterMappings = boundSqlUpdate.getParameterMappings();

        // where之前参数,如果存在,直接插入
        List<ParameterMapping> beforeParameterMapping = buildBeforeParameterMapping(where_before, boundSql);
        if (beforeParameterMapping != null && !beforeParameterMapping.isEmpty()) {
            parameterMappings.addAll(0, beforeParameterMapping);
        }

        // where之后参数
        if (afterParameterMapping != null && !afterParameterMapping.isEmpty()) {
            parameterMappings.addAll(afterParameterMapping);
        }

        MetaObject boundSqlMeta = SystemMetaObject.forObject(boundSqlUpdate);
        Object parameters = boundSqlMeta.getValue("additionalParameters");
        Object metaParameters = boundSqlMeta.getValue("metaParameters");

        metaObject.setValue("boundSql.sql", boundSqlUpdate.getSql());
        metaObject.setValue("boundSql.metaParameters", metaParameters);
        metaObject.setValue("boundSql.parameterMappings", parameterMappings);
        metaObject.setValue("boundSql.additionalParameters", parameters);

        //注意：以下这行千万不要执行,不然会直接修改原来接口的sql
//        metaObject.setValue("delegate.mappedStatement.sqlSource", sqlSource);
    }

    /**
     * 构建TextSqlNode
     *
     * @param condition 条件
     * @param nodeDto   NodeDto
     */
    public void buildTextSqlNode(Condition condition, NodeDto nodeDto) {
        String sql = condition.value();
        String param = nodeDto.getKey();
        sql = sql.replace(":" + param, String.format("#{%s}", param));
        sql = joinSql(sql);
        TextSqlNode sqlNode = new TextSqlNode(sql);
        conditionNodes.add(sqlNode);
    }

    /**
     * 构建ForEachSqlNode
     *
     * @param condition 条件
     * @param nodeDto   NodeDto
     */
    public void buildForEachSqlNode(Condition condition, NodeDto nodeDto) {
        String sql = condition.value();
        String param = nodeDto.getKey();
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");
        // 列表
        String sqlBefore = sql.split(":" + param)[0];
        String node_before = joinSql(sqlBefore);
        SqlNode staticTextSqlNode = new StaticTextSqlNode(node_before);
        conditionNodes.add(staticTextSqlNode);

        List<SqlNode> nodes = new ArrayList<>();
        nodes.add(new StaticTextSqlNode("#{item}"));
        SqlNode content = new MixedSqlNode(nodes);
        SqlNode sqlNode = new ForEachSqlNode(configuration, content, param, null, "index", "item",
                "(", ")", ",");
        conditionNodes.add(sqlNode);
    }

    /**
     * 构建StaticTextSqlNode
     *
     * @param condition 条件
     */
    public void buildStaticTextSqlNode(Condition condition) {
        String value = condition.value();
        String sql = joinSql(value);
        SqlNode staticTextSqlNode = new StaticTextSqlNode(sql);
        conditionNodes.add(staticTextSqlNode);
    }

    /**
     * 数据转换
     *
     * @param nodeDto NodeDto
     */
    public void checkParamToMap(NodeDto nodeDto) {
        Object formatValue = nodeDto.getFormatValue();
        BoundSql boundSql = handler.getBoundSql();
        MetaObject paramMap = SystemMetaObject.forObject(boundSql.getParameterObject());
        //判断是否需要把对象转换成map
        String param = nodeDto.getKey();
        int index = param.indexOf(".");
        if (index > 0) {
            String key = param.substring(0, index);
            if (!formatSet.contains(key)) {
                convertParamToMap(paramMap, key);
                formatSet.add(key);
            }
        }

        paramMap.setValue(param, formatValue);
    }

    //把对象转换成map
    public void convertParamToMap(MetaObject paramMap, String key) {
        Map<String, Object> objMap = new HashMap<>();
        Object value = paramMap.getValue(key);

        MetaObject metaObject = SystemMetaObject.forObject(value);
        String[] names = metaObject.getGetterNames();
        for (String name : names) {
            objMap.put(name, metaObject.getValue(name));
        }

        paramMap.setValue(key, objMap);
    }

    /**
     * 构建原生节点
     *
     * @param sql sql
     * @return 原生节点
     */
    private SqlNode buildOriginNode(String sql) {
        SqlNode node = null;
        //是否有#{}
        Pattern pattern = Pattern.compile(SqlConst.MYBATIS_REGEX);
        Matcher matcher = pattern.matcher(sql);
        boolean match = matcher.find();
        if (match) {
            SqlNode textSqlNode = new TextSqlNode(sql);
            node = new MixedSqlNode(List.of(textSqlNode));
        } else {
            node = new StaticTextSqlNode(sql);
        }
        return node;
    }

    /**
     * 处理where后面的参数映射
     * @param sql sql,已自动处理为?占位符
     * @param boundSql boundSql
     * @return 参数映射
     */
    private List<ParameterMapping> buildAfterParameterMapping(String sql, BoundSql boundSql) {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings == null || parameterMappings.isEmpty()) {
            return null;
        }
        Pattern pattern = Pattern.compile(SqlConst.MYBATIS_);
        Matcher matcher = pattern.matcher(sql);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        if (count == 0) {
            return null;
        }
        int startIndex = parameterMappings.size() - count;
        return parameterMappings.subList(startIndex, count);
    }

    /**
     * 处理where前面的参数映射
     * @param sql sql,已自动处理为?占位符
     * @param boundSql boundSql
     * @return 参数映射
     */
    private List<ParameterMapping> buildBeforeParameterMapping(String sql, BoundSql boundSql) {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings == null || parameterMappings.isEmpty()) {
            return null;
        }
        Pattern pattern = Pattern.compile(SqlConst.MYBATIS_);
        Matcher matcher = pattern.matcher(sql);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        if (count == 0) {
            return null;
        }
        return parameterMappings.subList(0, count);
    }

    /**
     * 连接sql
     *
     * @param sql sql
     * @return 加入连接符后的sql
     */
    private String joinSql(String sql) {
        String joinMark = handleJoinMark(sql);
        // 格式：连接符+sql
        sql = String.format(" %s %s", joinMark, sql);
        return sql;
    }

    /**
     * 处理连接符
     *
     * @param sql sql
     * @return 连接符号
     */
    private static String handleJoinMark(String sql) {
        // 判断是否已有连接符
        String mark = null;
        String defaultMark = SqlConst.JOIN_MARK_AND;
        //获取第一个连接字符
        int index = -1;//首个字符序号
        for (int i = 0; i < sql.length(); i++) {
            String s = sql.substring(i, i + 1);
            if (s.equals("a") || s.equals("A")) {
                index = i;
                mark = SqlConst.JOIN_MARK_AND;
                break;
            } else if (s.equals("o") || s.equals("O")) {
                index = i;
                mark = SqlConst.JOIN_MARK_OR;
                break;
            }
        }
        // 找不到,默认 and
        if (index == -1) {
            return defaultMark;
        }
        // 直接假设了sql长度至少大于3,连接符后必须是个空格
        String space = " ";
        String joinMark = mark + space;
        // 全部转小写比较
        String markText = sql.substring(index, index + joinMark.length());
        markText = markText.toLowerCase();
        // 是否自带了连接符
        boolean hasJoinMark = markText.equals(joinMark);
        if (hasJoinMark) {
            return "";
        }
        return defaultMark;
    }

    /**
     * 处理条件字段后面的括号内容
     *
     * @param condition 条件
     * @param nodeDto   对象
     */
    private void handleEndNode(Condition condition, NodeDto nodeDto) {
        String sql = condition.value();
        String key = nodeDto.getKey();
        String param = ":" + key;
        int index = sql.indexOf(param);
        String endText = sql.substring(index + param.length());
        if (!endText.trim().isEmpty()) {
            StaticTextSqlNode sqlNode = new StaticTextSqlNode(endText);
            this.conditionNodes.add(sqlNode);
        }
    }
}
