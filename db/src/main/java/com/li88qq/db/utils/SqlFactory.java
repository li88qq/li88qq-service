package com.li88qq.db.utils;

import com.li88qq.db.dto.BeanDto;

import java.util.Arrays;
import java.util.List;

/**
 * sql构建工厂
 *
 * @author li88qq
 * @version 1.0 2021/12/10 23:37
 */
public class SqlFactory {

    /**
     * 构建insert语句
     *
     * @param ignoreRepeat 是否忽略重复
     * @param beanDto      类分析对象
     * @param count        实体数量
     * @return insert语句
     */
    public static String buildInsertSql(boolean ignoreRepeat, BeanDto beanDto, int count) {
        StringBuilder sql = new StringBuilder();
        String[] fields = beanDto.getFields();
        String tableName = beanDto.getTableName();

        //字段sql
        String fieldSql = String.join(",", fields);
        //问号sql
        String markSql = buildInsertMarkSql(count, fields.length);

        //insert ignore into tableName (id,name) values (?,?),(?,?);
        sql.append("insert ");
        if (ignoreRepeat) {
            sql.append("ignore ");
        }
        sql.append("into ").append(tableName);
        sql.append(" (").append(fieldSql).append(") ");
        sql.append("values ").append(markSql).append(";");

        return sql.toString();
    }

    /**
     * 构建insert问号sql
     *
     * @param count     实体数量
     * @param fieldSize 字段数量
     * @return 问号?占位符
     */
    private static String buildInsertMarkSql(int count, int fieldSize) {
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sql.append("(");
            for (int j = 0; j < fieldSize; j++) {
                sql.append("?").append(",");
            }
            sql.deleteCharAt(sql.lastIndexOf(","));
            sql.append(")").append(",");
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        return sql.toString();
    }

    /**
     * 构建更新sql
     *
     * @param beanDto 类分析对象
     * @return 更新sql
     */
    public static String buildUpdateSql(BeanDto beanDto) {
        StringBuilder sql = new StringBuilder();
        String tableName = beanDto.getTableName();
        String[] fields = beanDto.getFields();
        String[] idNames = beanDto.getIdNames();

        List<String> idList = Arrays.asList(idNames);

        //update tableName set k=?,v=? where id = ?
        sql.append("update ").append(tableName);
        sql.append(" set ");
        for (String field : fields) {
            if (idList.contains(field)) {
                continue;
            }
            sql.append(field).append(" = ").append("?").append(",");
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        buildWhereIdSql(sql, idList);

        return sql.toString();
    }

    /**
     * 构建删除sql
     *
     * @param beanDto 类分析对象
     * @return 更新sql
     */
    public static String buildDeleteSql(BeanDto beanDto) {
        StringBuilder sql = new StringBuilder();
        String tableName = beanDto.getTableName();
        List<String> idList = Arrays.asList(beanDto.getIdNames());

        //delete from tableName where id = ?
        sql.append("delete from ").append(tableName);
        buildWhereIdSql(sql, idList);
        return sql.toString();
    }

    /**
     * 构建update,delete 中where id sql
     *
     * @param sql    sql
     * @param idList id字段列表
     */
    private static void buildWhereIdSql(StringBuilder sql, List<String> idList) {
        sql.append(" where ");
        for (int i = 0; i < idList.size(); i++) {
            if (i > 0) {
                sql.append(" and ");
            }
            sql.append(idList.get(i)).append(" = ").append("?");
        }
        sql.append(";");
    }

    /**
     * 新增或保存
     * <p>
     * 关键:
     * INSERT INTO TABLE(COLUMN,COLUMN,COLUMN)
     * VALUES (#{column},#{column},#{column}),(#{column},#{column},#{column}) as alias
     * ON DUPLICATE KEY
     * UPDATE COLUMN=alias.column,column=alias.column,column=alias.column
     * </p>
     *
     * @param beanDto 类分析对象
     * @param count   循环次数
     * @return 保存或更新sql
     */
    public static String buildSaveOrUpdateSql(BeanDto beanDto, int count) {
        StringBuilder sql = new StringBuilder();
        String[] fields = beanDto.getFields();
        String tableName = beanDto.getTableName();

        //字段sql
        String fieldSql = String.join(",", fields);
        //问号sql
        String markSql = buildInsertMarkSql(count, fields.length);

        //insert into tableName (id,name) values (?,?),(?,?) as alias on duplicate key update id = alias.id,name = alias.name;
        //需要mysql版本:8.0.20及以上
        sql.append("insert ").append("into ").append(tableName);
        sql.append(" (").append(fieldSql).append(") ");
        sql.append("values ").append(markSql);
        sql.append(" as alias"); //使用别名
        sql.append(" on duplicate key update ");

        for (String field : fields) {
            sql.append(field).append(" = ").append("alias.").append(field).append(",");
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        sql.append(";");

        return sql.toString();
    }

    /**
     * 批量修改
     *
     * <p>
     * update table set name = case id when 1 then 1 when 2 then 2 end where id in (ids);
     * </p>
     *
     * @param beanDto 类分析对象
     * @param count   循环次数
     * @return 批量更新sql
     */
    public static String buildUpdateBatchSql(BeanDto beanDto, int count) {
        String tableName = beanDto.getTableName();
        String[] idNames = beanDto.getIdNames();
        String[] fields = beanDto.getFields();

        String idName = idNames[0];

        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" set ");
        for (String field : fields) {
            if (field.equals(idName)) {
                continue;
            }
            sql.append(field).append(" = ").append("case ").append(idName);
            sql.append(" when ? then ?".repeat(count));
            sql.append(" end,");
        }
        sql.deleteCharAt(sql.lastIndexOf(","));
        sql.append(" where ").append(idName).append(" in ");
        sql.append("(").append("?,".repeat(count));
        sql.deleteCharAt(sql.lastIndexOf(","));
        sql.append(");");

        return sql.toString();
    }

}
