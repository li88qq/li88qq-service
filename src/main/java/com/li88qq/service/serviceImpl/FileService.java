package com.li88qq.service.serviceImpl;

import com.li88qq.service.service.IFileService;
import com.li88qq.service.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理
 *
 * @author li88qq
 * @version 1.0 2021/8/6 22:31
 */
@Service
public class FileService implements IFileService {

    @Value("${img.path}")
    private String imgPath;
    @Value("${img.prefix}")
    private String imgPrefix;

    /**
     * 保存图片
     *
     * @param file
     * @param tag
     * @return
     */
    @Override
    public String saveImage(MultipartFile file, String tag) {
        String fileUrl = FileUtil.saveFile(file, imgPath, tag);
        return String.join("", imgPrefix, fileUrl);
    }
}
