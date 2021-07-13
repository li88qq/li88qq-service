package com.li88qq.service.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码
 */
@Controller
public class KaptchaController {

    @Resource
    private DefaultKaptcha defaultKaptcha;

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String code = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(code);
        response.setContentType("image/jpeg");

        try (ServletOutputStream stream = response.getOutputStream()) {
            ImageIO.write(image, "jpg", stream);
        } catch (IOException e) {
        }
    }
}