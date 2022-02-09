package com.li88qq.login.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * 验证码工具类
 *
 * @author li88qq
 * @version 1.0 2022/1/19 23:07
 */
public class KaptchaUtil {

    /**
     * 获取验证码base64
     *
     * @param defaultKaptcha
     * @return
     */
    public static String getBase64(DefaultKaptcha defaultKaptcha) {
        return getBase64(defaultKaptcha, null);
    }

    /**
     * 获取验证码base64
     *
     * @param defaultKaptcha
     * @param code
     * @return
     */
    public static String getBase64(DefaultKaptcha defaultKaptcha, String code) {
        if (code == null || code.equals("")) {
            code = defaultKaptcha.createText();
        }
        BufferedImage image = defaultKaptcha.createImage(code);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("操作失败!");
        }
    }
}
