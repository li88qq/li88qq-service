package com.li88qq.service.controller;

import com.li88qq.service.constant.AcLogConst;
import com.li88qq.service.constant.annitions.AcLog;
import com.li88qq.service.constant.enumeration.ActionType;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.IdBo;
import com.li88qq.service.dto.IdsBo;
import com.li88qq.service.request.nav.GetNavPageBo;
import com.li88qq.service.request.nav.SaveNavBo;
import com.li88qq.service.request.nav.UpdateTypeBo;
import com.li88qq.service.response.GetNavListVo;
import com.li88qq.service.response.GetNavPageVo;
import com.li88qq.service.service.INavService;
import org.fastquery.page.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 导航管理
 *
 * @author li88qq
 * @version 1.0 2021/8/20 23:17
 */
@RestController
@RequestMapping("/nav")
public class NavController {

    @Resource
    private INavService navService;

    /**
     * 批量保存
     *
     * @param list
     * @return
     */
    @PostMapping("/save")
    @AcLog(acType = ActionType.SAVE, title = "新增导航", prefix = AcLogConst.NAV)
    public BaseResponse save(@RequestBody List<SaveNavBo> list) {
        return navService.save(list);
    }

    /**
     * 分页查询
     *
     * @param bo
     * @return
     */
    @GetMapping("/page")
    public Page<GetNavPageVo> getPage(GetNavPageBo bo) {
        return navService.getPage(bo);
    }

    /**
     * 查询列表
     *
     * @param key
     * @return
     */
    @GetMapping("/list")
    public List<GetNavListVo> getList(@RequestParam(required = false) String key) {
        return navService.getList(key);
    }

    /**
     * 删除
     *
     * @param bo
     * @return
     */
    @PostMapping("/delete")
    @AcLog(acType = ActionType.DELETE, title = "删除导航", detail = "bo", prefix = AcLogConst.NAV)
    public BaseResponse delete(@RequestBody IdsBo bo) {
        return navService.delete(bo);
    }

    /**
     * 点击导航
     *
     * @param bo
     * @return
     */
    @PostMapping("/addCount")
    public BaseResponse addCount(@RequestBody IdBo bo) {
        return navService.addCount(bo);
    }

    /**
     * 新增导航分类
     *
     * @param types
     * @return
     */
    @PostMapping("/saveType")
    @AcLog(acType = ActionType.SAVE, title = "新增导航分类", prefix = AcLogConst.NAV)
    public BaseResponse saveType(@RequestBody List<String> types) {
        return navService.saveType(types);
    }

    /**
     * 修改导航分类
     *
     * @param bo
     * @return
     */
    @PostMapping("/updateType")
    @AcLog(acType = ActionType.UPDATE, title = "修改导航分类", detail = "bo", prefix = AcLogConst.NAV)
    public BaseResponse updateType(@RequestBody UpdateTypeBo bo) {
        return navService.updateType(bo);
    }

    /**
     * 删除导航分类
     *
     * @param bo
     * @return
     */
    @PostMapping("/deleteType")
    @AcLog(acType = ActionType.DELETE, title = "删除导航分类", detail = "bo", prefix = AcLogConst.NAV)
    public BaseResponse deleteType(@RequestBody IdBo bo) {
        return navService.deleteType(bo);
    }
}
