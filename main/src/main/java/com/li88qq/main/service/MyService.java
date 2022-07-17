package com.li88qq.main.service;

import com.li88qq.main.dto.my.BaseInfoVo;
import com.li88qq.main.dto.my.DetailVo;

/**
 * 我的
 *
 * @author li88qq
 * @version 1.0 2022/7/17 22:30
 */
public interface MyService {

    /**
     * 查询基本信息
     */
    BaseInfoVo base();

    /**
     * 详细信息
     */
    DetailVo detail();
}
