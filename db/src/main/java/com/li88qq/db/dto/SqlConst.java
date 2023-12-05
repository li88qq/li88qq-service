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

    //Condition自定义参数格式: :param
    public static final String PLACE_ = ":[a-z_]+(\\.[a-zA-Z0-9_]+)?";
    //sql where占位符
    public static final String PLACE_WHERE = ":where";
    //db占位符
    public static final String TAG_db = "@{db}.";

    //mybatis占位符 #{} ${},包括[a-zA-Z_$.]字符，无空格
    public static final String MYBATIS_P = "\\#\\{[a-zA-Z_\\$\\.]+\\}";
    public static final String MYBATIS_$ = "\\$\\{[a-zA-Z_\\$\\.]+\\}";
    public static final String MYBATIS_REGEX = "[\\$\\#]\\{[a-zA-Z_\\$\\.]+\\}";
    public static final String MYBATIS_ = "\\?";

    // 连接符
    public static final String JOIN_MARK_AND = "and";
    public static final String JOIN_MARK_OR = "or";

}
