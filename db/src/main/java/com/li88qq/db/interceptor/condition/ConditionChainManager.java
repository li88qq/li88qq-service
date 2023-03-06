package com.li88qq.db.interceptor.condition;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.dto.SqlConst;
import com.li88qq.db.dto.sql.NodeDto;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.SqlNode;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 条件职责链控制器
 *
 * @author li88qq
 * @version 1.0 2023/3/5 20:16
 */
public class ConditionChainManager {

    private final StatementHandler handler;
    private final Condition[] conditions;
    private final LinkedList<SqlNode> sqlNodes = new LinkedList<>();

    private final LinkedList<NodeChain> chains = new LinkedList<>();

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
        this.chains.add(chain);
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

            Object value = nodeDto.getValue();
            if (value == null) {
                continue;
            }

            //职责链处理
            for (NodeChain chain : chains) {
                boolean check = chain.check(value.getClass());
                //如果该节点有处理,就退出当前字段职责链
                if (check) {
                    chain.format();
                    chain.build();
                    break;
                }
            }
        }
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


    public LinkedList<NodeChain> getChains() {
        return chains;
    }

    public LinkedList<SqlNode> getSqlNodes() {
        return sqlNodes;
    }
}
