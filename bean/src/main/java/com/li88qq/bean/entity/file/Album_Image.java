package com.li88qq.bean.entity.file;

import com.li88qq.db.annotion.Table;

/**
 * 图片和相册关系
 *
 * @author li88qq
 * @version 1.0 2022/5/8 23:07
 */
@Table
public class Album_Image {

    private Long albumId;//相册id
    private Long imageId;//图片id

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
