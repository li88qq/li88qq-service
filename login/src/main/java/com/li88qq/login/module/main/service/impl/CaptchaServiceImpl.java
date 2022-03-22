package com.li88qq.login.module.main.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.li88qq.login.config.RedisKey;
import com.li88qq.login.module.main.dto.captcha.GetCaptchaVo;
import com.li88qq.login.module.main.service.CaptchaService;
import com.li88qq.login.util.KaptchaUtil;
import com.li88qq.utils.UUIDUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 验证码
 *
 * @author li88qq
 * @version 1.0 2022/1/19 23:12
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource
    private DefaultKaptcha defaultKaptcha;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取验证码
     */
    @Override
    public GetCaptchaVo getCaptcha() {
        String code = UUIDUtil.uuid8();
        String text = defaultKaptcha.createText();
        String captcha = KaptchaUtil.getBase64(defaultKaptcha, text);
        String prefix = "data:image/jpeg;base64,";
        captcha = String.join("", prefix, captcha);

        RedisKey rk = RedisKey.P_CAPTCHA;
        stringRedisTemplate.opsForValue().set(rk.getKey() + code, text, rk.getTime(), rk.getTimeUnit());

        GetCaptchaVo vo = new GetCaptchaVo();
        vo.setCode(code);
        vo.setCaptcha(captcha);
        return vo;
    }
}
