package com.li88qq.login.module.main.service.impl;

import com.li88qq.bean.entity.system.User;
import com.li88qq.db.core.BaseMapper;
import com.li88qq.login.config.RedisKey;
import com.li88qq.login.dao.UserMapper;
import com.li88qq.login.module.main.dto.login.LoginForm;
import com.li88qq.login.module.main.dto.login.LoginVo;
import com.li88qq.login.module.main.dto.register.RegisterForm;
import com.li88qq.login.module.main.service.LoginService;
import com.li88qq.login.util.Password;
import com.li88qq.login.util.PasswordUtil;
import com.li88qq.utils.UUIDUtil;
import com.li88qq.utils.response.BaseResponse;
import com.li88qq.utils.response.ResponseUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2022/3/21 23:19
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private BaseMapper baseMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录
     */
    @Override
    public LoginVo login(LoginForm form) {
        String username = form.getUsername();
        String password = form.getPassword();
        String code = form.getCode();
        String checkCode = form.getCheckCode();

        //验证码校验
        String captchaKey = RedisKey.P_CAPTCHA.getKey() + checkCode;
        String codeValue = stringRedisTemplate.opsForValue().get(captchaKey);
        if (codeValue == null) {
            throw ResponseUtil.exception("验证码已过期,请重新获取");
        }
        if (!code.equals(codeValue)) {
            throw ResponseUtil.exception("验证码错误");
        }

        //用户名密码校验
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw ResponseUtil.exception("用户名或密码错误");
        }
        boolean check = PasswordUtil.check(password, user.getSalt(), user.getPassword());
        if (!check) {
            throw ResponseUtil.exception("用户名或密码错误");
        }

        //移除验证码
        stringRedisTemplate.delete(captchaKey);
        //放入token
        RedisKey pToken = RedisKey.P_TOKEN;
        String token = UUIDUtil.uuid19();
        stringRedisTemplate.opsForValue().set(pToken.getKey() + token, user.getId().toString(), pToken.getTime(),
                pToken.getTimeUnit());
        //修改最近登录信息
        //保存登录记录

        LoginVo vo = new LoginVo();
        vo.setToken(token);
        return vo;
    }

    /**
     * 注册
     */
    @Override
    public BaseResponse register(RegisterForm form) {
        String username = form.getUsername();
        String password = form.getPassword();
        String mobile = form.getMobile();

        User user = userMapper.findByUsername(username);
        if (user != null) {
            return ResponseUtil.error("用户名已被注册, 请重新注册.");
        }
        Password passwordData = PasswordUtil.create(password);

        user = new User();
        user.setUsername(username);
        user.setPassword(passwordData.getPassword());
        user.setSalt(passwordData.getSalt());
        user.setMobile(mobile);

        baseMapper.saveId(user, Long.class);

        return ResponseUtil.okMsg("注册成功!");
    }
}
