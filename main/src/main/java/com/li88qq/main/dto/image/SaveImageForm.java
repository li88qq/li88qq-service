package com.li88qq.main.dto.image;

import org.springframework.web.multipart.MultipartFile;

/**
 * 保存图片
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:19
 */
public class SaveImageForm {

    private MultipartFile file;
    private Integer imageType;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }
}
