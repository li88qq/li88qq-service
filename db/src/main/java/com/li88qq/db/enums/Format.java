package com.li88qq.db.enums;

/**
 * 参数格式化处理
 *
 * @author li88qq
 * @version 1.0 2022/12/5 22:36
 */
public enum Format {

    //字符串
    LIKE,// %value%

    //时间戳
    TIME_MAX,//日期转时间戳,时间取最大
    TIME_MIN,//日期转时间戳,时间取最小
    TIMESTAMP,//转时间戳

    //数组
    IN,//

}
