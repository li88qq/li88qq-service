package com.li88qq.service.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;

/**
 * @author li88qq
 * @version 1.0 2021/8/6 23:26
 */
public class FileUtil {

    private static final String PATH_SEP = "/";

    public static String saveFile(MultipartFile file, String rootPath, String tag) {
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String fileType = originalFilename.substring(index);
        String filePath = buildPath(tag, fileType);
        File saveFile = new File(String.join("/", rootPath, filePath));
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

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

}
