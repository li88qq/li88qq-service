package com.li88qq.service.serviceImpl;

import com.li88qq.service.constant.enumeration.ResponseState;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.Password;
import com.li88qq.service.entity.User;
import com.li88qq.service.repo.UserRepo;
import com.li88qq.service.request.my.UpdatePasswordBo;
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
}
