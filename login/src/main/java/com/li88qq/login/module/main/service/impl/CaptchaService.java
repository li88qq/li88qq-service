package com.li88qq.login.module.main.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.li88qq.login.module.main.response.GetCaptchaVo;
import com.li88qq.login.module.main.service.ICaptchaService;
import com.li88qq.login.util.KaptchaUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 验证码
 *
 * @author li88qq
 * @version 1.0 2022/1/19 23:12
 */
@Service
public class CaptchaService implements ICaptchaService {

    @Resource
    private DefaultKaptcha defaultKaptcha;

    /**
     * 获取验证码
     *
     * @return
     */
    @Override
    public GetCaptchaVo getCaptcha() {
        String code = "123456";
        String captcha = KaptchaUtil.getBase64(defaultKaptcha);
        String prefix = "data:image/jpeg;base64,";
        captcha = String.join("", prefix, captcha);

        GetCaptchaVo vo = new GetCaptchaVo();
        vo.setCode(code);
        vo.setCaptcha(captcha);
        return vo;
    }
}
