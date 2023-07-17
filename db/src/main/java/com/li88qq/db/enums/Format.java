package com.li88qq.db.enums;

/**
 * 条件字段格式化
 *
 * @author li88qq
 * @version 1.0 2023/2/20 22:45
 */
public enum Format {

    //不处理
    NONE,

    //字符串,String类型参数
    LIKE,//like %xx%
    LIKE_L,//左like,%x
    LIKE_R,//右like,x%
    LIST_N,//转数字
    LIST_S,//转字符串

    //时间戳,LocalDate,LocalDateTime类型参数
    TS,//转时间戳
    TS_MIN,//转时间戳,时间取最小时间
    TS_MAX,//转时间戳,时间取最大时间

    //数组,包括 基本类型数组(int[]),列表(List<Integer>,Set<Integer>)
    IN,//in

}
