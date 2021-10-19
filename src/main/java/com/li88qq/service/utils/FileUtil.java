package com.li88qq.service.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;

/**
 * 文件工具类
 *
 * @author li88qq
 * @version 1.0 2021/8/6 23:26
 */
public class FileUtil {

    private static final String PATH_SEP = "/";//路径分隔符

    /**
     * 保存文件
     *
     * @param file     上传的文件
     * @param rootPath 保存目录
     * @param tag      文件标签
     * @return
     */
    public static String saveFile(MultipartFile file, String rootPath, String tag) {
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String fileType = originalFilename.substring(index);
        String filePath = buildPath(tag, fileType);
        File saveFile = new File(String.join(PATH_SEP, rootPath, filePath));
        File parentFile = saveFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(saveFile);
            outputStream.write(file.getBytes());
            outputStream.flush();
            return filePath;
        } catch (Exception e) {
            throw new RuntimeException("保存文件失败");
        } finally {
            close(outputStream);
        }
    }

    //构建路径,格式 /tag/年月日/uuid.xxx
    private static String buildPath(String tag, String fileType) {
        String yyMMdd = DateUtil.format(LocalDateTime.now(), "yyMMdd");
        String fileName = UUIDUtil.uuid19();

        StringBuilder sb = new StringBuilder();
        sb.append(PATH_SEP).append(tag);
        sb.append(PATH_SEP).append(yyMMdd);
        sb.append(PATH_SEP).append(fileName).append(fileType);
        return sb.toString();
    }

    /**
     * 关闭流
     *
     * @param closeable
     */
    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

}
