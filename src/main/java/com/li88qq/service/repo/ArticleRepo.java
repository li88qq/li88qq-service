package com.li88qq.service.repo;

import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;
import org.fastquery.page.Page;
import org.fastquery.page.Pageable;
import org.fastquery.where.Condition;

import java.util.List;
import java.util.Map;

public interface ArticleRepo extends QueryRepository {

    @Query(value = "select * from Article #{#where} order by id desc", countField = "id")
    @Condition(" uid = :uid")
    @Condition(" and title like :{bo.title}")
    @Condition(" and state =:state")
    @Condition(" and open =:open")
    @Condition(" and original =:original")
    @Condition(" and createDate >= :beginDate")
    @Condition(" and createDate <= :endDate")
    @Condition(" and readCount >= :beginCount")
    @Condition(" and readCount <= :endCount")
    Page<List<Map<String, Object>>> findPage(@Param("uid") Long uid, @Param("title") String title, @Param("state") Integer state,
                                             @Param("open") Integer open, @Param("original") Integer original, @Param("beginDate") Long beginDate,
                                             @Param("title") Long endDate, @Param("beginCount") Integer beginCount, @Param("endCount") Integer endCount,
                                             Pageable pageable);
}
