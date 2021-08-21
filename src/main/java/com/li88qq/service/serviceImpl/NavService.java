package com.li88qq.service.serviceImpl;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.IdBo;
import com.li88qq.service.dto.IdsBo;
import com.li88qq.service.entity.Navigation;
import com.li88qq.service.entity.NavigationType;
import com.li88qq.service.repo.NavRepo;
import com.li88qq.service.repo.NavTypeRepo;
import com.li88qq.service.request.nav.GetNavPageBo;
import com.li88qq.service.request.nav.SaveNavBo;
import com.li88qq.service.request.nav.UpdateTypeBo;
import com.li88qq.service.response.GetNavListVo;
import com.li88qq.service.response.GetNavPageVo;
import com.li88qq.service.response.NavVo;
import com.li88qq.service.service.INavService;
import com.li88qq.service.utils.ResponseUtil;
import com.li88qq.service.utils.SessionUtil;
import com.li88qq.service.utils.StringUtil;
import com.li88qq.service.utils.TypeUtil;

import org.fastquery.page.Page;
import org.fastquery.page.Pageable;
import org.fastquery.page.PageableImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 导航管理
 *
 * @author li88qq
 * @version 1.0 2021/8/21 9:49
 */
@Service
public class NavService implements INavService {

    @Resource
    private NavRepo navRepo;
    @Resource
    private NavTypeRepo navTypeRepo;

    /**
     * 保存
     *
     * @param list
     * @return
     */
    @Override
    public BaseResponse save(List<SaveNavBo> list) {
        if (list == null || list.isEmpty()) {
            return ResponseUtil.error("保存记录为空");
        }

        Long uid = SessionUtil.getUid();

        List<Navigation> navList = new ArrayList<>();
        list.forEach(a -> {
            String name = StringUtil.trim(a.getName());
            String url = StringUtil.trim(a.getUrl());
            String remark = StringUtil.trim(a.getRemark());
            Long typeId = a.getTypeId();

            if (name.equals("")) {
                return;
            }
            if (url.equals("") || !(url.startsWith("http://") || url.startsWith("https://"))) {
                return;
            }
            if (typeId == null || typeId < 0) {
                typeId = 0L;
            }
            Navigation navigation = new Navigation();
            navigation.setUid(uid);
            navigation.setTypeId(typeId);
            navigation.setName(name);
            navigation.setUrl(url);
            navigation.setRemark(remark);
            navList.add(navigation);
        });
        if (navList.isEmpty()) {
            return ResponseUtil.error("保存记录为空");
        }

        int count = navRepo.save(true, navList);
        return ResponseUtil.okMsg("操作成功!保存记录数" + count);
    }

    /**
     * 分页查询
     *
     * @param bo
     * @return
     */
    @Override
    public Page<GetNavPageVo> getPage(GetNavPageBo bo) {
        String name = StringUtil.like(bo.getName());
        String url = StringUtil.like(bo.getUrl());
        String remark = StringUtil.like(bo.getRemark());

        Long uid = SessionUtil.getUid();
        Pageable pageable = new PageableImpl(bo.getPage(), bo.getSize());

        Page<Map<String, Object>> pageData = navRepo.findPage(uid, bo.getTypeId(), name, url, remark, pageable);
        return pageData.convert(GetNavPageVo.class);
    }

    /**
     * 查询列表
     *
     * @param key
     * @return
     */
    @Override
    public List<GetNavListVo> getList(String key) {
        key = StringUtil.like(key);
        Long uid = SessionUtil.getUid();

        List<NavigationType> types = navTypeRepo.findList(uid);
        if (types.isEmpty()) {
            return new ArrayList<>();
        }

        List<GetNavListVo> voList = new ArrayList<>();
        Map<Long, GetNavListVo> maps = new HashMap<>();
        types.forEach(a -> {
            GetNavListVo vo = new GetNavListVo();
            List<NavVo> vos = new ArrayList<>();
            vo.setTypeId(a.getId());
            vo.setTypeName(a.getName());
            vo.setList(vos);
            voList.add(vo);
            maps.put(a.getId(), vo);
        });


        List<Map<String, Object>> navList = navRepo.findList(uid, key);
        if (navList.isEmpty()) {
            return voList;
        }

        List<NavVo> vos = null;
        Long typeId = null;
        GetNavListVo vo = null;
        for (Map<String, Object> map : navList) {
            typeId = TypeUtil.getLong(map.get("typeId"));
            vo = maps.get(typeId);
            if (vo == null) {
                continue;
            }
            vos = vo.getList();

            NavVo navVo = new NavVo();
            navVo.setId(TypeUtil.getLong(map.get("id")));
            navVo.setName(TypeUtil.getString(map.get("name")));
            navVo.setUrl(TypeUtil.getString(map.get("url")));
            navVo.setRemark(TypeUtil.getString(map.get("remark")));
            vos.add(navVo);
        }
        return voList;

    }

    /**
     * 删除
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse delete(IdsBo bo) {
        String ids = bo.getIds();
        List<Long> list = StringUtil.toIds(ids);
        if (list.isEmpty()) {
            return ResponseUtil.error("操作记录为空");
        }
        Long uid = SessionUtil.getUid();
        navRepo.deleteByIds(uid, list);
        return ResponseUtil.ok();
    }

    /**
     * 增加点击数
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse addCount(IdBo bo) {
        Long id = bo.getId();
        Long uid = SessionUtil.getUid();
        navRepo.updateCount(uid, id);
        return ResponseUtil.ok();
    }

    @Override
    public BaseResponse saveType(List<String> types) {
        if (types == null || types.isEmpty()) {
            return ResponseUtil.error("操作记录为空");
        }
        Set<String> set = new HashSet<>();
        types.forEach(a -> {
            String type = StringUtil.trim(a);
            if (!type.equals("")) {
                set.add(StringUtil.trim(a));
            }
        });
        if (set.isEmpty()) {
            return ResponseUtil.error("操作记录为空");
        }

        Long uid = SessionUtil.getUid();
        List<NavigationType> list = new ArrayList<>();
        set.forEach(a -> {
            NavigationType type = new NavigationType();
            type.setUid(uid);
            type.setName(a);
            list.add(type);
        });
        navTypeRepo.save(true, list);
        return ResponseUtil.ok();
    }

    /**
     * 修改分类名称
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse updateType(UpdateTypeBo bo) {
        String name = bo.getName();
        name = StringUtil.trim(name);
        if (name.equals("")) {
            return ResponseUtil.error("名称不能为空");
        }
        Long uid = SessionUtil.getUid();
        navTypeRepo.updateName(uid, bo.getId(), name);
        return ResponseUtil.ok();
    }

    /**
     * 删除导航分类
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse deleteType(IdBo bo) {
        Long id = bo.getId();
        Long uid = SessionUtil.getUid();
        navTypeRepo.tx(() -> {
            navTypeRepo.deleteById(uid, id);
            navRepo.deleteByType(uid, id);
            return 1;
        });

        return ResponseUtil.ok();
    }

}
