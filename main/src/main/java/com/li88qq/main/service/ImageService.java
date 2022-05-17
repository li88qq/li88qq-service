package com.li88qq.main.service;

import com.li88qq.main.dto.image.SaveImageForm;

/**
 * 图片服务
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:20
 */
public interface ImageService {

    /**
     * 保存图片,返回路径
     */
    String save(SaveImageForm form);
}
