package com.li88qq.login.module.admin.service.impl;

import com.li88qq.bean.entity.am.system.AmUser;
import com.li88qq.bean.web.redis.RedisKey;
import com.li88qq.bean.web.redis.RedisUtil;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.bean.web.response.ResponseUtil;
import com.li88qq.login.dao.AmUserMapper;
import com.li88qq.login.module.admin.dto.sms.GetAmSmsForm;
import com.li88qq.login.module.admin.service.AmSmsService;
import com.li88qq.login.util.SmsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 短信验证码
 *
 * @author li88qq
 * @version 1.0 2022/3/27 21:56
 */
@Service
public class AmSmsServiceImpl implements AmSmsService {

    @Resource
    private SmsUtil smsUtil;
    @Resource
    private AmUserMapper amUserMapper;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取短信验证码
     */
    @Override
    public BaseResponse getSms(GetAmSmsForm form) {
        //手机号码是否已注册
        String mobile = form.getMobile();
        AmUser user = amUserMapper.findByMobile(mobile);
        if (user == null) {
            return ResponseUtil.error("该号码未注册!");
        }

        String code = smsUtil.smsCode();
        boolean send = smsUtil.send(mobile, code);
        if (!send) {
            return ResponseUtil.error("请重新获取验证码!");
        }

        redisUtil.set(RedisKey.AM_SMS_CODE, mobile, code);
        return ResponseUtil.okMsg("验证码已发送, 请注意查收.");
    }
}
