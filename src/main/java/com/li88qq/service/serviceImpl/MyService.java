package com.li88qq.service.serviceImpl;

import com.li88qq.service.constant.enumeration.ResponseState;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.Password;
import com.li88qq.service.entity.User;
import com.li88qq.service.repo.UserRepo;
import com.li88qq.service.request.my.UpdatePasswordBo;
import com.li88qq.service.request.my.UpdateProfileBo;
import com.li88qq.service.service.IMyService;
import com.li88qq.service.utils.DateUtil;
import com.li88qq.service.utils.PasswordUtil;
import com.li88qq.service.utils.ResponseUtil;
import com.li88qq.service.utils.SessionUtil;
import org.fastquery.service.FQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyService implements IMyService {

    @Resource
    private UserRepo userRepo;

    @Override
    public BaseResponse updatePassword(UpdatePasswordBo bo) {
        Long uid = SessionUtil.getUid();
        User user = userRepo.find(User.class, uid);
        boolean check = PasswordUtil.check(bo.getOldPassword(), user.getSalt(), user.getPassword());
        if (!check) {
            return ResponseUtil.error("密码错误");
        }
        Password password = PasswordUtil.create(bo.getPassword());
        if (password == null) {
            return ResponseUtil.response(ResponseState.FAIL);
        }

        User updateUser = FQuery.reset(User.class);
        updateUser.setId(uid);
        updateUser.setPassword(password.getPassword());
        updateUser.setSalt(password.getSalt());
        updateUser.setLastLoginDate(DateUtil.getTimestamp());
        updateUser.setLastLoginIp(SessionUtil.getIp());

        userRepo.update(updateUser);
        return ResponseUtil.ok();
    }

    /**
     * 修改个人信息
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse updateProfile(UpdateProfileBo bo) {
        Long uid = SessionUtil.getUid();
        String ip = SessionUtil.getIp();

        String nickname = bo.getNickname();
        String mobile = bo.getMobile();
        String email = bo.getEmail();

        //校验格式
        User user = FQuery.reset(User.class);
        user.setId(uid);
        user.setNickname(nickname);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setLastLoginDate(DateUtil.getTimestamp());
        user.setLastLoginIp(ip);

        userRepo.executeUpdate(user);
        return ResponseUtil.ok();
    }
}
