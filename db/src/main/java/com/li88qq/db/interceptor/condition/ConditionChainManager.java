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
        String whereBefore = strings[0];
        SqlNode whereBeforeNode = new StaticTextSqlNode(whereBefore);
        sqlNodes.add(whereBeforeNode);

        //where语句
        if (!conditionNodes.isEmpty()) {
            SqlNode whereNodes = new MixedSqlNode(conditionNodes);
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

        //注意：以下这行千万不要执行,不然会直接修改原来接口的sql
//        metaObject.setValue("delegate.mappedStatement.sqlSource", sqlSource);
    }

    /**
     * 构建tTextSqlNode
     *
     * @param condition 条件
     * @param nodeDto   NodeDto
     */
    public void buildTextSqlNode(Condition condition, NodeDto nodeDto) {
        String sql = condition.value();
        String param = nodeDto.getKey();
        sql = sql.replace(":" + param, String.format("#{%s}", param));
        TextSqlNode sqlNode = new TextSqlNode(String.join("", " and ", sql));
        conditionNodes.add(sqlNode);
    }

    public void buildForEachSqlNode() {

    }

    public void buildStaticTextSqlNode() {

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
}
