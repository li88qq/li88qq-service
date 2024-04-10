package com.li88qq.service.utils.file;

import com.li88qq.service.constant.SysConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 文件工具类
 *
 * @author li88qq
 * @version 1.0 2024/1/20 13:00
 */
public class FileUtil {

    /**
     * 保存图片
     *
     * @param image    文件
     * @param filePath 文件路径
     */
    public static void save(MultipartFile image, String filePath) {
        byte[] bytes = new byte[1024 * 1024];
        try {
            BufferedInputStream inputStream = new BufferedInputStream(image.getInputStream());
            String path = SysConfig.IMG_PATH + filePath;
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path));
            int read = -1;
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            close(inputStream);
            close(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            }
        }
    }

    /**
     * 处理文件路径
     *
     * @param module   模块前缀
     * @param filePath 文件路径
     * @return 处理后的文件路径
     */
    public static String handleImageUrl(String module, String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return filePath;
        }
        if (module == null) {
            module = "";
        }
        // 格式：域名/模块/前缀/文件路径
        String path = String.format("/%s/%s/%s", SysConfig.IMAGE_PREFIX, module, filePath);
        path = path.replaceAll("/{2,}", "/");
        return SysConfig.ASSET_HOST + path;
    }

}
