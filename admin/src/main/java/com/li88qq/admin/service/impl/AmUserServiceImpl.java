package com.li88qq.admin.service.impl;

import com.li88qq.admin.dao.amuser.AmUserMapper;
import com.li88qq.admin.dto.amuser.AddAmUserForm;
import com.li88qq.admin.dto.amuser.AmUserInfo;
import com.li88qq.admin.dto.amuser.AmUserPageForm;
import com.li88qq.admin.dto.amuser.AmUserPageVo;
import com.li88qq.admin.service.AmUserService;
import com.li88qq.bean.entity.am.system.AmUser;
import com.li88qq.bean.web.response.BaseResponse;
import com.li88qq.bean.web.response.ResponseUtil;
import com.li88qq.bean.web.session.SessionUtil;
import com.li88qq.bean.web.session.UserToken;
import com.li88qq.db.core.BaseMapper;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
import com.li88qq.db.dto.TPage;
import com.li88qq.utils.RegexUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户(后台)管理
 *
 * @author li88qq
 * @version 1.0 2022/3/28 17:51
 */
@Service
public class AmUserServiceImpl implements AmUserService {

    @Resource
    private AmUserMapper amUserMapper;
    @Resource
    private BaseMapper baseMapper;

    /**
     * 查询用户(后台)信息
     */
    @Override
    public AmUserInfo getInfo() {
        UserToken userToken = SessionUtil.getAmSession();
        Long uid = userToken.getUid();
        return amUserMapper.findInfo(uid);
    }

    /**
     * 分页查询用户(后台)信息
     */
    @Override
    public TPage<AmUserPageVo> getPage(AmUserPageForm form) {
        Pageable pageable = new Pageable(form.getPage(), form.getPageSize());
        Page<AmUserPageVo> page = amUserMapper.findPage(form, pageable);
        page.forEach(vo -> {
            String mobile = vo.getMobile();
            vo.setMobile(RegexUtil.markMobile(mobile));
        });
        return page.build();
    }

    /**
     * 添加用户(后台)
     */
    @Override
    public BaseResponse add(AddAmUserForm form) {
        AmUser amUser = amUserMapper.findByUsername(form.getUsername());
        if (amUser != null) {
            return ResponseUtil.error("该用户名已注册");
        }
        amUser = amUserMapper.findByMobile(form.getMobile());
        if (amUser != null) {
            return ResponseUtil.error("该手机号码已注册");
        }
        amUser = new AmUser();
        amUser.setUsername(form.getUsername());
        amUser.setMobile(form.getMobile());

        baseMapper.save(amUser);
        return ResponseUtil.ok();
    }

}
