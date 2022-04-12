package com.li88qq.login.module.admin.service.impl;

import com.li88qq.bean.entity.am.system.AmLoginLog;
import com.li88qq.bean.entity.am.system.AmUser;
import com.li88qq.bean.web.redis.RedisKey;
import com.li88qq.bean.web.redis.RedisUtil;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.bean.web.response.ResponseUtil;
import com.li88qq.bean.web.session.SessionUtil;
import com.li88qq.bean.web.session.UserToken;
import com.li88qq.db.core.BaseMapper;
import com.li88qq.login.dao.AmLoginLogMapper;
import com.li88qq.login.dao.AmUserMapper;
import com.li88qq.login.module.admin.dto.login.AmLoginForm;
import com.li88qq.login.module.admin.dto.login.AmLoginVo;
import com.li88qq.login.module.admin.service.AmLoginService;
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
public class AmLoginServiceImpl implements AmLoginService {

    @Resource
    private AmUserMapper amUserMapper;
    @Resource
    private BaseMapper baseMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private AmLoginLogMapper amLoginLogMapper;

    /**
     * 登录
     */
    @Override
    public AmLoginVo login(AmLoginForm form) {
        String mobile = form.getMobile();
        String code = form.getCode();

        //验证码校验
        String codeValue = redisUtil.get(RedisKey.AM_SMS_CODE, mobile, String.class);
        if (codeValue == null) {
            throw ResponseUtil.exception("验证码已过期,请重新获取");
        }
        if (!code.equals(codeValue)) {
            throw ResponseUtil.exception("验证码错误");
        }

        //用户名密码校验
        AmUser user = amUserMapper.findByMobile(mobile);
        if (user == null) {
            throw ResponseUtil.exception("手机未注册");
        }

        long uid = user.getId();
        String ip = SessionUtil.getIp();
        //修改最近登录信息
        updateLastLogin(user, ip);
        //保存登录记录
        Long loginId = saveLoginLog(uid, ip);
        String token = UUIDUtil.uuid19();
        //更新redis信息
        updateLoginRedis(user, loginId, token);

        AmLoginVo vo = new AmLoginVo();
        vo.setToken(token);
        return vo;
    }

    //更新最后登录信息
    private void updateLastLogin(AmUser user, String ip) {
        AmUser update = BaseMapper.reset(AmUser.class);
        update.setId(user.getId());
        update.setLastLoginDate(user.getLoginDate());
        update.setLastLoginIp(user.getLoginIp());
        update.setLoginDate(DateUtil.getTimestamp());
        update.setLoginIp(ip);
        baseMapper.updateNoNull(update);
    }

    //保存登录记录
    private Long saveLoginLog(Long uid, String ip) {
        AmLoginLog loginLog = new AmLoginLog();
        loginLog.setUid(uid);
        loginLog.setLoginIp(ip);
        Long loginId = baseMapper.saveId(loginLog, Long.class);
        return loginId;
    }

    //登录后设置redis
    private void updateLoginRedis(AmUser user, Long loginId, String token) {
        //移除验证码
        redisUtil.delete(RedisKey.AM_SMS_CODE, user.getMobile());
        //放入token
        UserToken userToken = new UserToken();
        userToken.setUid(user.getId());
        userToken.setLoginId(loginId);
        userToken.setToken(token);
        userToken.setLoginDate(DateUtil.getTimestamp());

        SessionUtil.setAmSession(token, userToken);
    }

    /**
     * 登出
     */
    @Override
    public BaseResponse logout() {
        UserToken userToken = SessionUtil.getAmSession();
        if (userToken != null) {
            Long uid = userToken.getUid();
            Long loginId = userToken.getLoginId();

            SessionUtil.deleteAmSession();

            amLoginLogMapper.updateLogoutDate(loginId, uid, DateUtil.getTimestamp());
        }

        return ResponseUtil.ok();
    }
}
