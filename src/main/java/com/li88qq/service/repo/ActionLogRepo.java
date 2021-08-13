package com.li88qq.service.repo;

import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;
import org.fastquery.page.Page;
import org.fastquery.page.Pageable;
import org.fastquery.where.Condition;

import java.util.Map;

/**
 * @author li88qq
 * @version 1.0 2021/8/12 22:31
 */
public interface ActionLogRepo extends QueryRepository {

    @Query("select id,acType,title,detail,createDate,ip from ActionLog #{#where} order by id desc")
    @Condition(" uid = :uid")
    @Condition(" and acType = :acType")
    @Condition(" and createDate >= :beginDate")
    @Condition(" and createDate <= :endDate")
    @Condition(" and title like :title")
    @Condition(" and detail like :detail")
    @Condition(" and ip like :ip")
    Page<Map<String, Object>> getPage(@Param("uid") Long uid, @Param("acType") Integer acType, @Param("title") String title,
                                      @Param("detail") String detail, @Param("beginDate") Long beginDate, @Param("endDate") Long endDate,
                                      @Param("ip") String ip, Pageable pageable);
}
