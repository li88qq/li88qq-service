package com.li88qq.service.module.my.service;

import com.li88qq.service.module.my.dto.my.GetMyInfoVo;
import com.li88qq.service.module.my.dto.my.GetMyMenuListVo;

import java.util.List;

/**
 * @author li88qq
 * @version 1.0 2023/12/31 15:40
 */
public interface MyService {

    GetMyInfoVo getInfo();

    List<GetMyMenuListVo> getMenuList();
}
