package com.li88qq.base.redis;

/**
 * redis库枚举
 *
 * @author li88qq
 * @version 1.0 2022/1/29 23:28
 */
public enum RedisDB {

    DB_0(0),//系统信息
    DB_1(1),//前台session
    DB_2(2),//后台session
    DB_3(3),//验证码,短信验证码
    DB_4(4),
    DB_5(5),
    DB_6(6),
    DB_7(7),
    DB_8(8),
    DB_9(9),
    DB_10(10),
    DB_11(11),
    DB_12(12),
    DB_13(13),
    DB_14(14),
    DB_15(15),
    ;

    private int index;

    RedisDB(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
