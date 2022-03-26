package com.li88qq.login.module.main.service.impl;

import com.li88qq.bean.entity.system.LoginLog;
import com.li88qq.bean.entity.system.User;
import com.li88qq.bean.web.redis.RedisKey;
import com.li88qq.bean.web.redis.RedisUtil;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.bean.web.response.ResponseUtil;
import com.li88qq.bean.web.session.SessionUtil;
import com.li88qq.bean.web.session.UserToken;
import com.li88qq.db.core.BaseMapper;
import com.li88qq.login.dao.LoginLogMapper;
import com.li88qq.login.dao.UserMapper;
import com.li88qq.login.module.main.dto.login.LoginForm;
import com.li88qq.login.module.main.dto.login.LoginVo;
import com.li88qq.login.module.main.dto.register.RegisterForm;
import com.li88qq.login.module.main.service.LoginService;
import com.li88qq.login.util.Password;
import com.li88qq.login.util.PasswordUtil;
import com.li88qq.utils.DateUtil;
import com.li88qq.utils.UUIDUtil;
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
    private RedisUtil redisUtil;
    @Resource
    private LoginLogMapper loginLogMapper;

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
        String codeValue = redisUtil.get(RedisKey.P_CAPTCHA, checkCode, String.class);
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

        long uid = user.getId();
        String ip = SessionUtil.getIp();
        //修改最近登录信息
        updateLastLogin(user, ip);
        //保存登录记录
        Long loginId = saveLoginLog(uid, ip);
        String token = UUIDUtil.uuid19();
        //更新redis信息
        updateLoginRedis(user, loginId, checkCode, token);

        LoginVo vo = new LoginVo();
        vo.setToken(token);
        return vo;
    }

    //更新最后登录信息
    private void updateLastLogin(User user, String ip) {
        User update = BaseMapper.reset(User.class);
        update.setId(user.getId());
        update.setLastLoginDate(user.getLoginDate());
        update.setLastLoginIp(user.getLoginIp());
        update.setLoginDate(DateUtil.getTimestamp());
        update.setLoginIp(ip);
        baseMapper.updateNoNull(update);
    }

    //保存登录记录
    private Long saveLoginLog(Long uid, String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUid(uid);
        loginLog.setLoginIp(ip);
        Long loginId = baseMapper.saveId(loginLog, Long.class);
        return loginId;
    }

    //登录后设置redis
    private void updateLoginRedis(User user, Long loginId, String checkCode, String token) {
        //移除验证码
        redisUtil.delete(RedisKey.P_CAPTCHA, checkCode);
        //放入token
        UserToken userToken = new UserToken();
        userToken.setUid(user.getId());
        userToken.setLoginId(loginId);
        userToken.setToken(token);
        userToken.setLoginDate(DateUtil.getTimestamp());

        SessionUtil.setSession(token, userToken);
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

    /**
     * 登出
     */
    @Override
    public BaseResponse logout() {
        UserToken userToken = SessionUtil.getSession();
        if (userToken != null) {
            Long uid = userToken.getUid();
            Long loginId = userToken.getLoginId();

            SessionUtil.deleteSession();

            loginLogMapper.updateLogoutDate(loginId, uid, DateUtil.getTimestamp());
        }

        return ResponseUtil.ok();
    }
}
