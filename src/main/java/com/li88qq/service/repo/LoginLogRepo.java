package com.li88qq.service.repo;

import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;
import org.fastquery.page.Page;
import org.fastquery.page.Pageable;
import org.fastquery.where.Condition;

import java.util.Map;

/**
 * 登录记录
 */
public interface LoginLogRepo extends QueryRepository {

    @Query("select id,state,loginType,createDate,loginIp,updateDate from LoginLog #{#where} order by id desc")
    @Condition(" uid = :uid")
    @Condition(" and state = :state")
    @Condition(" and loginType = :loginType")
    @Condition(" and createDate >= :beginDate")
    @Condition(" and createDate <= :endDate")
    @Condition(" and loginIp like :loginIp")
    Page<Map<String, Object>> getPage(@Param("uid") Long uid, @Param("state") Integer state, @Param("loginType") Integer loginType,
                                      @Param("beginDate") Long beginDate, @Param("endDate") Long endDate,
                                      @Param("loginIp") String loginIp, Pageable pageable);
}
