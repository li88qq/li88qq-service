package com.li88qq.db.interceptor.chains;

import com.li88qq.db.annotion.InsertId;
import com.li88qq.db.dto.SqlConst;
import com.li88qq.db.dto.sql.SqlDto;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * InsertId注解处理
 *
 * @author li88qq
 * @version 1.0 2023/3/1 22:02
 */
@Component
public class InsertIdChain implements InterceptorChain {

    @Override
    public boolean execute(StatementHandler handler, Method method) {
        boolean insertId = method.isAnnotationPresent(InsertId.class);
        if (!insertId) {
            return true;
        }
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        BoundSql boundSql = (BoundSql) metaObject.getValue("boundSql");
        MetaObject paramMap = SystemMetaObject.forObject(boundSql.getParameterObject());

        SqlDto sqlDto = (SqlDto) paramMap.getValue(SqlConst.PARAM_DTO);

        String key = String.format(SqlConst.INSERT_ID_FORMAT, SqlConst.PARAM_T, sqlDto.getIdField());

        String[] keyProperties = new String[]{key};
        String[] keyColumns = new String[]{sqlDto.getIdColumn()};

        MetaObject statementMetaObject = SystemMetaObject.forObject(mappedStatement);
        statementMetaObject.setValue("keyProperties", keyProperties);
        statementMetaObject.setValue("keyColumns", keyColumns);

        return true;
    }
}
