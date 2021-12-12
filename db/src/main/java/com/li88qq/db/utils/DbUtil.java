package com.li88qq.db.utils;

import com.li88qq.db.annotation.Id;
import com.li88qq.db.dto.BeanDto;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

/**
 * db工具类
 *
 * @author li88qq
 * @version 1.0 2021/12/10 23:04
 */
public class DbUtil {

    /**
     * insert,返回主键id
     *
     * @param t          实体
     * @param idClass    主键类型
     * @param connection 连接
     * @param <T>        实体泛型
     * @param <K>        主键泛型
     * @return 主键id
     */
    public static <T, K> K insert(T t, Class<K> idClass, Connection connection) {
        try {
            Class<?> aClass = t.getClass();
            BeanDto beanDto = BeanUtil.buildBeanDto(aClass);
            String[] idNames = beanDto.getIdNames();
            int length = idNames.length;
            if (length == 0) {
                throw new RuntimeException("需要定义一个@Id注解的字段.");
            }
            if (length != 1) {
                throw new RuntimeException("该方法仅支持一个@Id注解的字段.");
            }
            String sql = SqlFactory.buildInsertSql(false, beanDto, 1);
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            String[] fields = beanDto.getFields();
            Field declaredField = null;
            int index = 1;
            for (String field : fields) {
                declaredField = aClass.getDeclaredField(field);
                if (!declaredField.canAccess(t)) {
                    declaredField.setAccessible(true);
                }
                statement.setObject(index++, declaredField.get(t));
            }
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            K id = null;
            if (resultSet.next()) {
                id = resultSet.getObject(1, idClass);
            }
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
        }
    }

    /**
     * 批量保存
     *
     * @param ignoreRepeat 是否忽略重复
     * @param list         实体列表
     * @param connection   连接
     * @param <T>          实体泛型
     * @return 插入记录数
     */
    public static <T> long insert(boolean ignoreRepeat, List<T> list, Connection connection) {
        if (list == null || list.isEmpty()) {
            return 0L;
        }
        try {
            Class<?> aClass = list.get(0).getClass();
            BeanDto beanDto = BeanUtil.buildBeanDto(aClass);
            String sql = SqlFactory.buildInsertSql(ignoreRepeat, beanDto, list.size());
            PreparedStatement statement = connection.prepareStatement(sql);

            String[] fields = beanDto.getFields();
            Field declaredField = null;
            int index = 1;
            for (T t : list) {
                for (String field : fields) {
                    declaredField = aClass.getDeclaredField(field);
                    if (!declaredField.canAccess(t)) {
                        declaredField.setAccessible(true);
                    }
                    statement.setObject(index++, declaredField.get(t));
                }
            }
            return statement.executeLargeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
        }
    }

    /**
     * update
     *
     * @param t          实体列表
     * @param connection 连接
     * @param <T>        实体泛型
     * @return 影响行数
     */
    public static <T> long update(T t, Connection connection) {
        try {
            Class<?> aClass = t.getClass();
            BeanDto beanDto = BeanUtil.buildBeanDto(aClass);
            String[] idNames = beanDto.getIdNames();
            String[] fields = beanDto.getFields();

            int idLength = idNames.length;
            if (idLength == 0) {
                throw new RuntimeException("需要定义至少一个@Id注解的字段");
            }
            if (idLength == fields.length) {
                throw new RuntimeException("该方法不支持字段全为@Id注解");
            }
            String sql = SqlFactory.buildUpdateSql(beanDto);
            PreparedStatement statement = connection.prepareStatement(sql);

            Field declaredField = null;
            int index = 1;
            //除id外注解
            for (String field : fields) {
                declaredField = aClass.getDeclaredField(field);
                if (declaredField.isAnnotationPresent(Id.class)) {
                    continue;
                }
                if (!declaredField.canAccess(t)) {
                    declaredField.setAccessible(true);
                }
                statement.setObject(index++, declaredField.get(t));
            }

            //id注解字段
            for (String field : idNames) {
                declaredField = aClass.getDeclaredField(field);
                if (!declaredField.canAccess(t)) {
                    declaredField.setAccessible(true);
                }
                if (declaredField.get(t) == null) {
                    throw new RuntimeException("@Id注解的字段值不能为空");
                }
                statement.setObject(index++, declaredField.get(t));
            }
            return statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
        }
    }

    /**
     * update批量
     *
     * @param list       实体列表
     * @param connection 连接
     * @param <T>        实体泛型
     * @return 影响行数
     */
    public static <T> long updateBatch(List<T> list, Connection connection) {
        if (list == null || list.isEmpty()) {
            return 0L;
        }
        try {
            Class<?> aClass = list.get(0).getClass();
            BeanDto beanDto = BeanUtil.buildBeanDto(aClass);
            String[] idNames = beanDto.getIdNames();
            String[] fields = beanDto.getFields();
            int idLength = idNames.length;
            if (idLength == 0) {
                throw new RuntimeException("需要定义至少一个@Id注解的字段");
            }
            if (idLength > 1) {
                throw new RuntimeException("该方法仅支持一个@Id注解");
            }
            if (idLength == fields.length) {
                throw new RuntimeException("该方法不支持字段全为@Id注解");
            }

            String idName = idNames[0];
            String sql = SqlFactory.buildUpdateBatchSql(beanDto, list.size());
            //update table set name = case id when 1 then 1 when 2 then 2 end where id in (ids);


            return 0L;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
        }
    }

    /**
     * saveOrUpdate
     *
     * @param t          实体对象
     * @param connection 连接
     * @param <T>        实体泛型
     * @return 影响行数
     */
    public static <T> long saveOrUpdate(T t, Connection connection) {
        return 0L;
    }

    /**
     * 删除
     *
     * @param t          实体对象
     * @param connection 连接
     * @param <T>        实体泛型
     * @return 影响行数
     */
    public static <T> long delete(T t, Connection connection) {
        return 0L;
    }

    /**
     * 关闭连接
     *
     * @param connection 连接
     */
    private static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
