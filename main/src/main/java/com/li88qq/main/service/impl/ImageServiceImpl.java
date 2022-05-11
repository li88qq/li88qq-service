package com.li88qq.main.service.impl;

import com.li88qq.bean.entity.file.Image;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.main.dto.image.SaveImageForm;
import com.li88qq.main.service.ImageService;
import org.springframework.stereotype.Service;

/**
 * @author li88qq
 * @version 1.0 2022/5/11 23:29
 */
@Service
public class ImageServiceImpl implements ImageService {

    /**
     * 保存
     */
    @Override
    public BaseResponse save(SaveImageForm form) {
        Image image = new Image();
        return null;
    }
}
