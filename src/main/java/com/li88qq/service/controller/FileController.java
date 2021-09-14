package com.li88qq.service.controller;

import com.li88qq.service.constant.AcLogConst;
import com.li88qq.service.constant.annitions.AcLog;
import com.li88qq.service.constant.enumeration.ActionType;
import com.li88qq.service.service.IFileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private IFileService fileService;

    @PostMapping("/saveImage")
    @AcLog(acType = ActionType.FILE, title = "保存图片", detail = "tag", prefix = AcLogConst.FILE)
    public String saveImage(@RequestParam("file") MultipartFile file, @RequestParam("tag") String tag) {
        return fileService.saveImage(file, tag);
    }

}
