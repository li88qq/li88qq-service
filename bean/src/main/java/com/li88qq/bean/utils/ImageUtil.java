package com.li88qq.bean.utils;

import com.li88qq.bean.dto.BaseResult;
import com.li88qq.bean.enums.ImageType;
import com.li88qq.utils.DateUtil;
import com.li88qq.utils.UUIDUtil;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 图片工具
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:42
 */
public class ImageUtil {

    /**
     * 保存图片
     *
     * @param file      图片
     * @param uid       用户
     * @param imageType 图片类型
     * @return 保存路径
     */
    public static BaseResult<String> save(MultipartFile file, Long uid, ImageType imageType, String rootPath) {
        if (file == null || file.isEmpty()) {
            return BaseResult.error("文件为空!");
        }
        BaseResult<String> checkExt = getFileExt(file);
        if (!checkExt.isSuccess()) {
            return checkExt;
        }

        String ext = checkExt.getData();
        String path = buildPath(uid, imageType, ext);

        try (OutputStream outputStream = new FileOutputStream(rootPath + path)) {
            outputStream.write(file.getBytes());
            outputStream.flush();
        } catch (Exception e) {
            return BaseResult.error("文件保存失败!");
        }

        return BaseResult.ok(path);
    }

    //校验并获取图片文件类型
    private static BaseResult<String> getFileExt(MultipartFile file) {
        String contentType = file.getContentType();
        String name = file.getOriginalFilename();
        List<MediaType> mediaTypes = List.of(MediaType.IMAGE_PNG, MediaType.IMAGE_JPEG, MediaType.IMAGE_GIF);
        String ext = null;
        if (contentType != null) {
            for (MediaType mediaType : mediaTypes) {
                if (mediaType.getType().equals(contentType)) {
                    ext = mediaType.getSubtype();
                    break;
                }
            }
        } else if (name != null) {
            int index = name.lastIndexOf(".");
            if (index > 0) {
                String _ext = name.substring(index + 1);
                if (!_ext.equals("")) {
                    for (MediaType mediaType : mediaTypes) {
                        if (mediaType.getSubtype().equals(_ext)) {
                            ext = _ext;
                            break;
                        }
                    }
                }
            }
        }
        if (ext == null) {
            return BaseResult.error("不支持的图片类型");
        }
        return BaseResult.ok(ext);
    }

    /**
     * 构建图片路径
     *
     * @param uid       用户id
     * @param imageType 图片类型
     * @return 图片路径
     */
    public static String buildPath(Long uid, ImageType imageType, String ext) {
        String path = null;
        String pathSep = "/";
        //如果是文章图片,直接是 /图片类型/年月日/随机8位.类型
        //个人图片,/图片类型/uid/年月日/随机码8位.类型
        String date = DateUtil.format("yyMMdd");
        String fileName = UUIDUtil.uuid8() + "." + ext;
        StringBuilder sb = new StringBuilder();
        if (imageType == ImageType.image) {
            path = String.join(pathSep, pathSep, imageType.getName(), uid.toString(), date, fileName);
        } else {//文章,
            path = String.join(pathSep, pathSep, imageType.getName(), date, fileName);
        }
        return path;
    }

}
