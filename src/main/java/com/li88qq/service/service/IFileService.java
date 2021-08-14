package com.li88qq.service.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author li88qq
 * @version 1.0 2021/8/6 22:29
 */
public interface IFileService {

    String saveImage(MultipartFile file, String tag);
}
