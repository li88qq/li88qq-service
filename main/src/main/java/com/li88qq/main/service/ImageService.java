package com.li88qq.main.service;

import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.main.dto.image.SaveImageForm;

/**
 * 图片服务
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:20
 */
public interface ImageService {

    /**
     * 保存
     */
    BaseResponse save(SaveImageForm form);
}
