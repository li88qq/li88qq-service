package com.li88qq.main.service.impl;

import com.li88qq.bean.dto.BaseResult;
import com.li88qq.bean.entity.file.Image;
import com.li88qq.bean.enums.ImageType;
import com.li88qq.bean.utils.ImageUtil;
import com.li88qq.bean.web.response.ResponseUtil;
import com.li88qq.bean.web.session.SessionUtil;
import com.li88qq.bean.web.session.UserToken;
import com.li88qq.db.core.BaseMapper;
import com.li88qq.main.dto.image.SaveImageForm;
import com.li88qq.main.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 图片服务
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:29
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private BaseMapper baseMapper;
    @Value("${img.path}")
    private String imgPath;

    /**
     * 保存图片,返回路径
     */
    @Override
    public String save(SaveImageForm form) {
        ImageType imageType = ImageType.parse(form.getImageType());
        if (imageType == null) {
            throw ResponseUtil.exception("参数错误");
        }
        MultipartFile file = form.getFile();
        UserToken userToken = SessionUtil.getSession();
        Long uid = userToken.getUid();
        BaseResult<String> saveResult = ImageUtil.save(file, uid, imageType, imgPath);
        if (!saveResult.isSuccess()) {
            throw ResponseUtil.exception(saveResult.getMsg());
        }
        String path = saveResult.getData();

        Image image = new Image();
        image.setImageType(imageType.getType());
        image.setPath(path);
        image.setUid(uid);
        baseMapper.save(image);

        return path;
    }
}
