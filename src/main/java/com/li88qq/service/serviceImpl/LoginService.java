package com.li88qq.service.serviceImpl;

import com.li88qq.service.constant.enumeration.LoginState;
import com.li88qq.service.constant.enumeration.LoginType;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.SessionUser;
import com.li88qq.service.entity.LoginLog;
import com.li88qq.service.entity.User;
import com.li88qq.service.repo.LoginLogRepo;
import com.li88qq.service.repo.UserRepo;
import com.li88qq.service.request.LoginBo;
import com.li88qq.service.service.ILoginService;
import com.li88qq.service.utils.DateUtil;
import com.li88qq.service.utils.PasswordUtil;
import com.li88qq.service.utils.ResponseUtil;
import com.li88qq.service.utils.SessionUtil;
import org.fastquery.service.FQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class LoginService implements ILoginService {
    @Resource
    private UserRepo userRepo;
    @Resource
    private LoginLogRepo loginLogRepo;
    @Resource
    private RedisService redisService;

    @Override
    public BaseResponse login(LoginBo bo) {
        BaseResponse checkCode = checkCode(bo);
        if (!checkCode.success()) {
            return checkCode;
        }

        //校验用户
        String username = bo.getUsername();
        String password = bo.getPassword();
        User user = userRepo.findByUsername(username);
        LoginLog loginLog = null;
        String ip = SessionUtil.getIp();
        Integer loginType = LoginType.WEB.getType();
        if (user == null) {
            loginLog = new LoginLog(0L, LoginState.NOT_USER.getState(), username, loginType, ip);
            loginLogRepo.save(loginLog);
            return ResponseUtil.error("用户名或密码错误");
        }

        //校验密码
        Long userId = user.getId();
        String password_hash = user.getPassword();
        String slat = user.getSalt();
        boolean check = PasswordUtil.check(password, slat, password_hash);
        if (!check) {
            loginLog = new LoginLog(userId, LoginState.NOT_PASSWORD.getState(), password, loginType, ip);
            loginLogRepo.save(loginLog);
            return ResponseUtil.error("用户名或密码错误");
        }

        //保存登录记录
        loginLog = new LoginLog(userId, LoginState.SUCCESS.getState(), "", loginType, ip);
        Long loginId = loginLogRepo.saveToId(loginLog).longValue();

        //修改用户登录信息
        User updateUser = FQuery.reset(User.class);
        updateUser.setId(userId);
        updateUser.setLastLoginDate(user.getLoginDate());//上次登录时间
        updateUser.setLastLoginIp(user.getLoginIp());//上次登录ip
        updateUser.setLoginDate(DateUtil.getTimestamp());
        updateUser.setLoginIp(ip);
        userRepo.executeUpdate(updateUser);

        //设置session
        SessionUser sessionUser = new SessionUser();
        sessionUser.setUid(userId);
        sessionUser.setVisitor(username.equals("test"));
        sessionUser.setLoginId(loginId);
        redisService.setSessionUser(sessionUser);

        SessionUtil.setAttribute("user", sessionUser);

        return ResponseUtil.okMsg("登录成功");
    }

    @Override
    public BaseResponse logout() {
        // 更新登录记录的登出时间
        SessionUser user = SessionUtil.getUser();
        Long loginId = user.getLoginId();
        LoginLog loginLog = FQuery.reset(LoginLog.class);
        loginLog.setId(loginId);
        loginLog.setUpdateDate(DateUtil.getTimestamp());
        loginLogRepo.executeUpdate(loginLog);

        redisService.removeSession();

        //移除session
        SessionUtil.removeSession();
        return ResponseUtil.okMsg("您已登出平台！稍后将自动跳转到登录页面！");
    }

    //后台校验验证码
    private BaseResponse checkCode(LoginBo bo) {
        String code = bo.getCode().toLowerCase();
        HttpSession session = SessionUtil.getSession(false);
        String sessionId = session.getId();
        String codeValue = redisService.getCaptcha(sessionId);
        if (codeValue == null || codeValue.equals("")) {
            return ResponseUtil.error("验证码已过期,请重新获取验证码.");
        }
        if (!code.equals(codeValue.toLowerCase())) {
            return ResponseUtil.error("验证码错误");
        }

        redisService.removeCaptcha(sessionId);
        return ResponseUtil.ok();
    }

}
