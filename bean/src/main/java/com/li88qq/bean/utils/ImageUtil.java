package com.li88qq.bean.utils;

import com.li88qq.bean.dto.BaseResult;
import com.li88qq.bean.enums.ImageType;
import com.li88qq.utils.DateUtil;
import com.li88qq.utils.UUIDUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

        OutputStream outputStream = null;
        try {
            File saveFile = new File(rootPath + path);
            File parentFile = saveFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            outputStream = new FileOutputStream(saveFile);
            outputStream.write(file.getBytes());
            outputStream.flush();
        } catch (Exception e) {
            return BaseResult.error("文件保存失败!");
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
        }

        return BaseResult.ok(path);
    }

    //校验并获取图片文件类型
    private static BaseResult<String> getFileExt(MultipartFile file) {
        String contentType = file.getContentType();
        String name = file.getOriginalFilename();
        List<String> mediaTypes = List.of("image/jpeg", "image/png", "image/gif");
        List<String> imageTypes = List.of("jpg", "png", "gif");
        String ext = null;
        if (contentType != null) {
            int index = mediaTypes.indexOf(contentType);
            if (index != -1) {
                ext = contentType.substring("image/".length());
                if (ext.equals("jpeg")) {
                    ext = "jpg";
                }
            }
        } else if (name != null) {
            int index = name.lastIndexOf(".");
            if (index > 0) {
                String _ext = name.substring(index + 1);
                if (imageTypes.contains(_ext)) {
                    ext = _ext;
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
        if (imageType == ImageType.image) {
            path = String.join(pathSep, imageType.name(), uid.toString(), date, fileName);
        } else {//文章,
            path = String.join(pathSep, imageType.name(), date, fileName);
        }
        return pathSep + path;
    }

}
