package com.li88qq.common.utils.file;

import com.li88qq.common.dto.BaseResult;
import com.li88qq.common.utils.UUIDUtil;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 文件工具类
 *
 * @author li88qq
 * @version 1.0 2023/7/24 22:07
 */
public class FileUtil {

    /**
     * 保存图片
     *
     * @param file 文件
     * @param path 路径
     * @return 文件名
     */
    public static BaseResult<String> save(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) {
            return BaseResult.error("文件为空");
        }
        if (path == null || path.equals("")) {
            return BaseResult.error("保存路径为空");
        }
        String contentType = file.getContentType();
        if (contentType == null || contentType.equals("")) {
            return BaseResult.error("contentType为空");
        }

        String fileName = buildName(file.getContentType());
        String savePath = String.join("/", path, fileName);
        File saveFile = new File(savePath);
        File parentFile = saveFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        try {
            write(savePath, file.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return BaseResult.ok(fileName);
    }

    /**
     * 构建文件名
     *
     * @param contentType 文件类型
     * @return 文件名
     */
    private static String buildName(String contentType) {
        String fileName = UUIDUtil.uuid19();
        String ext = getExt(contentType);
        return String.join(".", fileName, ext);
    }

    /**
     * 获取文件后缀
     *
     * @param contentType 文件类型
     * @return 文件后缀
     */
    private static String getExt(String contentType) {
        MediaType mediaType = MediaType.valueOf(contentType);
        String subtype = mediaType.getSubtype();
        if (subtype.equals("jpeg")) {
            return "jpg";
        }
        return subtype;
    }

    /**
     * 写入文件
     *
     * @param file     文件名
     * @param byteData 文件内容
     * @throws Exception 异常
     */
    private static void write(String file, byte[] byteData) throws Exception {
        byte[] bytes = new byte[1024 * 1024];
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        InputStream inputStream = new ByteArrayInputStream(byteData);
        int read = 0;
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        outputStream.flush();
        close(outputStream);
        close(inputStream);
    }

    /**
     * 关闭流
     *
     * @param closeable 可关闭流
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
