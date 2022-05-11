package com.li88qq.main.controller;

import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.main.dto.image.SaveImageForm;
import com.li88qq.main.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 图片管理
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:19
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    @Resource
    private ImageService imageService;

    /**
     * 保存图片
     */
    @PostMapping("/save")
    public BaseResponse save(@RequestBody SaveImageForm form) {
        return imageService.save(form);
    }
}
