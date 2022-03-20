package com.li88qq.db.core;

/**
 * 操作类型
 *
 * @author li88qq
 * @version 1.0 2022/3/7 22:02
 */
enum QueryType {

    INSERT,//保存
    INSERT_ID,//保存并返回自增长id
    UPDATE,//修改
    UPDATE_NOT_NULL,//只修改非空字段
    DELETE,//删除

    ;
}
