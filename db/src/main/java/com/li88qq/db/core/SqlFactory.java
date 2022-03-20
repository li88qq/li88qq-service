package com.li88qq.db.core;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * sql构建工厂
 *
 * @author li88qq
 * @version 1.0 2022/3/7 23:16
 */
class SqlFactory {

    //参数占位符
    private static final String PLACE = "?";
    //分隔符
    private static final String SEP = ",";

    /**
     * 构建sql元数据
     *
     * @param queryType 操作类型
     * @param t         实体对象
     * @return sql元数据
     */
    public static <T> SqlMeta buildSql(QueryType queryType, T t) {
        SqlMeta sqlMeta = null;
        switch (queryType) {
            case INSERT, INSERT_ID -> sqlMeta = insert(t);
            case UPDATE -> sqlMeta = update(t);
            case UPDATE_NOT_NULL -> sqlMeta = updateNoNull(t);
            case DELETE -> sqlMeta = delete(t);
        }
        return sqlMeta;
    }

    /**
     * insert
     *
     * @param t 实体
     * @return SqlMeta
     */
    private static <T> SqlMeta insert(T t) {
        Class<?> aClass = t.getClass();
        ClassMeta classMeta = getClassMeta(aClass);
        String tableName = classMeta.getTableName();

        // 合并字段
        String[] fields = StringUtil.join(classMeta.getIds(), classMeta.getFields(), String.class);

        //占位符
        String fields_place = String.join(SEP, fields);
        String value_place = StringUtil.repeat(PLACE, fields.length, SEP);

        StringBuilder sb = new StringBuilder();
        // insert into tableName (fields_place) values (value_place);
        sb.append("insert into ").append(tableName).append(" (").append(fields_place).append(") values (").append(value_place).append(");");

        String sql = sb.toString();
        Object[] params = getFieldValue(t, fields);
        SqlMeta sqlMeta = new SqlMeta(sql, params);
        return sqlMeta;
    }

    /**
     * update
     *
     * @param t 实体
     * @return SqlMeta
     */
    private static <T> SqlMeta update(T t) {
        Class<?> aClass = t.getClass();
        ClassMeta classMeta = getClassMeta(aClass);
        String tableName = classMeta.getTableName();

        String[] ids = classMeta.getIds();
        String[] fields = classMeta.getFields();
        if (ids == null || ids.length == 0) {
            throw new RuntimeException("@Id为空!");
        }
        Object[] idValue = getFieldValue(t, ids);
        for (Object id : idValue) {
            if (id == null) {
                throw new RuntimeException("@Id字段值为空!");
            }
        }
        if (fields == null || fields.length == 0) {
            throw new RuntimeException("字段为空!");
        }
        //占位符
        String fields_place = StringUtil.kv(fields);
        String id_place = StringUtil.kvId(ids);

        StringBuilder sb = new StringBuilder();
        // update tableName set k=?,k=? where id = ?; update tableName set k=?,k=? where id = ? and k3=?;
        sb.append("update ").append(tableName).append(" set ").append(fields_place).append(" where ").append(id_place).append(";");

        String sql = sb.toString();
        // 合并字段处理参数
        String[] fieldArr = StringUtil.join(fields, ids, String.class);
        Object[] params = getFieldValue(t, fieldArr);

        SqlMeta sqlMeta = new SqlMeta(sql, params);
        return sqlMeta;
    }

    /**
     * update,非空字段
     *
     * @param t 实体
     * @return SqlMeta
     */
    private static <T> SqlMeta updateNoNull(T t) {
        Class<?> aClass = t.getClass();
        ClassMeta classMeta = getClassMeta(aClass);
        String tableName = classMeta.getTableName();

        String[] ids = classMeta.getIds();
        String[] fields = classMeta.getFields();
        if (ids == null || ids.length == 0) {
            throw new RuntimeException("@Id为空!");
        }
        Object[] idValue = getFieldValue(t, ids);
        for (Object id : idValue) {
            if (id == null) {
                throw new RuntimeException("@Id字段值为空!");
            }
        }
        if (fields == null || fields.length == 0) {
            throw new RuntimeException("字段为空!");
        }

        // 过滤非空的字段
        List<String> fieldList = new ArrayList<>();
        List<Object> paramList = new ArrayList<>();

        Field field = null;
        Object value = null;
        try {
            for (String f : fields) {
                field = aClass.getDeclaredField(f);
                if (!field.canAccess(t)) {
                    field.setAccessible(true);
                }
                value = field.get(t);
                if (value != null) {
                    fieldList.add(f);
                    paramList.add(value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("获取字段值异常");
        }
        if (fieldList.isEmpty()) {
            throw new RuntimeException("没有修改的字段");
        }

        //占位符
        String fields_place = StringUtil.kv(fieldList.toArray(new String[0]));
        String id_place = StringUtil.kvId(ids);

        StringBuilder sb = new StringBuilder();
        // update tableName set k=?,k=? where id = ?; update tableName set k=?,k=? where id = ? and k3=?;
        sb.append("update ").append(tableName).append(" set ").append(fields_place).append(" where ").append(id_place).append(";");

        String sql = sb.toString();
        // 合并字段处理参数
        Object[] params = StringUtil.join(paramList.toArray(new Object[0]), idValue, Object.class);

        SqlMeta sqlMeta = new SqlMeta(sql, params);
        return sqlMeta;
    }

    /**
     * 删除
     *
     * @param t 实体
     * @return SqlMeta
     */
    private static <T> SqlMeta delete(T t) {
        Class<?> aClass = t.getClass();
        ClassMeta classMeta = getClassMeta(aClass);
        String tableName = classMeta.getTableName();

        String[] ids = classMeta.getIds();
        if (ids == null || ids.length == 0) {
            throw new RuntimeException("@Id为空!");
        }
        //占位符
        String id_place = StringUtil.kvId(ids);

        StringBuilder sb = new StringBuilder();
        // delete from tableName where id = ?; delete from tableName where id = ? and k3=?;
        sb.append("delete from ").append(tableName).append(" where ").append(id_place).append(";");

        String sql = sb.toString();
        // 合并字段处理参数
        Object[] params = getFieldValue(t, ids);

        SqlMeta sqlMeta = new SqlMeta(sql, params);
        return sqlMeta;
    }

    /**
     * 获取实体对应字段值
     *
     * @param t      实体
     * @param fields 字段
     * @return 字段值
     */
    private static <T> Object[] getFieldValue(T t, String[] fields) {
        Object[] values = new Object[fields.length];
        try {
            Class<?> aClass = t.getClass();
            Field field = null;
            for (int i = 0; i < fields.length; i++) {
                field = aClass.getDeclaredField(fields[i]);
                if (!field.canAccess(t)) {
                    field.setAccessible(true);
                }
                values[i] = field.get(t);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return values;
    }

    /**
     * 构建class元数据
     *
     * @param aClass 实体类
     * @return class元数据
     */
    public static ClassMeta getClassMeta(Class<?> aClass) {
        String tableName = aClass.getSimpleName();
        Table table = aClass.getAnnotation(Table.class);
        if (table != null) {
            String tableValue = StringUtil.trim(table.value());
            if (!tableValue.equals("")) {
                tableName = tableValue;
            }
        }

        Field[] declaredFields = aClass.getDeclaredFields();
        List<String> idList = new ArrayList<>();
        List<String> fieldList = new ArrayList<>();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Id.class)) {
                idList.add(field.getName());
                continue;
            }
            fieldList.add(field.getName());
        }

        String[] ids = idList.toArray(new String[0]);
        String[] fields = fieldList.toArray(new String[0]);

        ClassMeta classMeta = new ClassMeta();
        classMeta.setTableName(tableName);
        classMeta.setIds(ids);
        classMeta.setFields(fields);

        return classMeta;
    }

    /**
     * 批量保存
     *
     * @param list         实体列表
     * @param ignoreRepeat 是否忽略重复主键
     * @return 影响行数
     */
    public static <T> SqlMeta buildSaveListSql(List<T> list, boolean ignoreRepeat) {
        Class<?> aClass = list.get(0).getClass();
        ClassMeta classMeta = getClassMeta(aClass);
        String tableName = classMeta.getTableName();
        // 合并字段
        String[] fields = StringUtil.join(classMeta.getIds(), classMeta.getFields(), String.class);

        //占位符
        String fields_place = String.join(SEP, fields);
        String value_place = StringUtil.repeatTwo(fields.length, list.size());

        StringBuilder sb = new StringBuilder();
        // insert into tableName (fields_place) values (value_place);
        sb.append("insert ");
        if (ignoreRepeat) {
            sb.append("ignore ");
        }
        sb.append("into ").append(tableName).append(" (").append(fields_place).append(") values ").append(value_place).append(";");

        String sql = sb.toString();
        Object[] params = getFieldValues(list, fields);
        SqlMeta sqlMeta = new SqlMeta(sql, params);
        return sqlMeta;
    }

    /**
     * 构建批量保存参数值
     *
     * @param list   实体列表
     * @param fields 所有字段
     * @return 参数值
     */
    private static <T> Object[] getFieldValues(List<T> list, String[] fields) {
        Class<?> aClass = list.get(0).getClass();
        List<Object> values = new ArrayList<>();
        Object value = null;
        Field field = null;
        try {
            for (T t : list) {
                for (String f : fields) {
                    field = aClass.getDeclaredField(f);
                    if (!field.canAccess(t)) {
                        field.setAccessible(true);
                    }
                    value = field.get(t);
                    values.add(value);
                }
            }
            return values.toArray(new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
