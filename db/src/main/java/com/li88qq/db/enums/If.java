package com.li88qq.db.enums;

/**
 * 动态语句加入条件
 *
 * @author li88qq
 * @version 1.0 2023/2/20 22:54
 */
public enum If {

    //不处理
    NONE,

    //非空
    NOT_NULL,

    //字符串
    NOT_BLANK,//非空字符,默认

    //数字
    GT,//大于0
    GT_EQ,//大于等于0

}
