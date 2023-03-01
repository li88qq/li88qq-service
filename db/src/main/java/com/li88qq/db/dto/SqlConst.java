package com.li88qq.db.dto;

/**
 * sql常量
 *
 * @author li88qq
 * @version 1.0 2023/3/1 23:25
 */
public class SqlConst {

    //MapperTemplate中实体对应参数
    public static final String PARAM_T = "t";
    //MapperTemplate中SqlDto对应参数
    public static final String PARAM_DTO = "dto";
    //分隔符号
    public static final String SEP = ",";
    //mybatis #{a.b}
    public static final String VALUE_FORMAT = "#{%s.%s}";
    //键值对 k = v
    public static final String KV_FORMAT = "%s = %s";

    //ignore
    public static final String IGNORE = "ignore";
    //insertId中key格式
    public static final String INSERT_ID_FORMAT = "%s.%s";


}
