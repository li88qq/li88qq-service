package com.li88qq.service.serviceImpl;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.SessionUser;
import com.li88qq.service.entity.User;
import com.li88qq.service.repo.LoginLogRepo;
import com.li88qq.service.repo.UserRepo;
import com.li88qq.service.request.LoginBo;
import com.li88qq.service.service.ILoginService;
import com.li88qq.service.utils.PasswordUtil;
import com.li88qq.service.utils.ResponseUtil;
import com.li88qq.service.utils.SessionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService implements ILoginService {
    @Resource
    private UserRepo userRepo;
    @Resource
    private LoginLogRepo loginLogRepo;

    @Override
    public BaseResponse login(LoginBo bo) {
        String username = bo.getUsername();
        String password = bo.getPassword();
        User user = userRepo.findByUsername(username);
        if (user == null) {
            return ResponseUtil.error("用户名或密码错误");
        }
        String password_hash = user.getPassword();
        String slat = user.getSalt();
        boolean check = PasswordUtil.check(password, slat, password_hash);
        if (!check) {
            return ResponseUtil.error("用户名或密码错误");
        }

        SessionUser sessionUser = new SessionUser();
        sessionUser.setUid(user.getId());
        sessionUser.setVisitor(false);
        SessionUtil.setAttribute("user", sessionUser);

        return ResponseUtil.okMsg("登录成功");
    }

    @Override
    public BaseResponse logout() {
        SessionUtil.removeSession();
        return ResponseUtil.ok();
    }

}
