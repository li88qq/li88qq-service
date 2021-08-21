package com.li88qq.service.service;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.IdBo;
import com.li88qq.service.dto.IdsBo;
import com.li88qq.service.request.nav.GetNavPageBo;
import com.li88qq.service.request.nav.SaveNavBo;
import com.li88qq.service.request.nav.UpdateTypeBo;
import com.li88qq.service.response.GetNavListVo;
import com.li88qq.service.response.GetNavPageVo;
import org.fastquery.page.Page;

import java.util.List;

/**
 * @author li88qq
 * @version 1.0 2021/8/20 23:30
 */
public interface INavService {

    BaseResponse save(List<SaveNavBo> list);

    Page<GetNavPageVo> getPage(GetNavPageBo bo);

    List<GetNavListVo> getList(String key);

    BaseResponse delete(IdsBo bo);

    BaseResponse addCount(IdBo bo);

    BaseResponse saveType(List<String> types);

    BaseResponse updateType(UpdateTypeBo bo);

    BaseResponse deleteType(IdBo bo);
}
