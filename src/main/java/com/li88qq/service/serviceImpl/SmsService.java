package com.li88qq.service.serviceImpl;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.entity.User;
import com.li88qq.service.repo.UserRepo;
import com.li88qq.service.request.sms.SmsCodeBo;
import com.li88qq.service.service.ISmsService;
import com.li88qq.service.utils.ResponseUtil;
import com.li88qq.service.utils.SmsUtil;
import com.li88qq.service.utils.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信服务
 *
 * @author li88qq
 * @version 1.0 2021/10/1 23:26
 */
public class SmsService implements ISmsService {

    @Resource
    private UserRepo userRepo;
    @Resource
    private RedisService redisService;

    /**
     * 发送短信验证码
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse sendSmsCode(SmsCodeBo bo) {
        //判断手机号码是否存在
        String mobile = bo.getMobile();
        User user = userRepo.findByMobile(mobile);
        if (user == null) {
            return ResponseUtil.error("该手机号码未绑定!");
        }
        //判断是否已存在未使用的验证码,5分钟内
        String code = redisService.getSmsCode(mobile);
        if (code != null) {
            return ResponseUtil.error("验证码已发送,请不要多次请求!");
        }
        //生成6位数验证码,发送验证码,保存到redis
        code = StringUtil.randomNumberCode(6);
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        try {
            SmsUtil.send(mobile, map);
        } catch (Exception e) {
            return ResponseUtil.error("验证码发送失败,请稍后再试!");
        }

        redisService.setSmsCode(mobile, code);

        return ResponseUtil.okMsg("验证码已发送!");
    }
}
