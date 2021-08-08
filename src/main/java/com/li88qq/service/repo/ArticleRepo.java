package com.li88qq.service.repo;

import com.li88qq.service.entity.Article;
import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;
import org.fastquery.page.Page;
import org.fastquery.page.Pageable;
import org.fastquery.where.Condition;

import java.util.Map;

public interface ArticleRepo extends QueryRepository {

    @Query(value = "select * from Article #{#where} order by id desc", countField = "id")
    @Condition(" uid = :uid")
    @Condition(" and state =:state")
    @Condition(" and open =:open")
    @Condition(" and original =:original")
    @Condition(" and createDate >= :beginDate")
    @Condition(" and createDate <= :endDate")
    @Condition(" and readCount >= :beginCount")
    @Condition(" and readCount <= :endCount")
    @Condition(" and title like :title")
    Page<Map<String, Object>> findPage(@Param("uid") Long uid, @Param("title") String title, @Param("state") Integer state,
                                       @Param("open") Integer open, @Param("original") Integer original, @Param("beginDate") Long beginDate,
                                       @Param("endDate") Long endDate, @Param("beginCount") Integer beginCount, @Param("endCount") Integer endCount,
                                       Pageable pageable);

    @Query(value = "select a.*,u.nickname as username from Article a left join User u on a.uid = u.id #{#where} order by a.id desc", countField = "a.id")
    @Condition(" a.state =0 and a.open = 1")
    @Condition(" and a.original =:original")
    @Condition(" and a.createDate >= :beginDate")
    @Condition(" and a.createDate <= :endDate")
    @Condition(" and a.readCount >= :beginCount")
    @Condition(" and a.readCount <= :endCount")
    @Condition(" and a.title like :title")
    @Condition(" and u.nickname like :username")
    Page<Map<String, Object>> findAllPage(@Param("title") String title, @Param("username") String username,
                                          @Param("original") Integer original, @Param("beginDate") Long beginDate,
                                          @Param("endDate") Long endDate, @Param("beginCount") Integer beginCount,
                                          @Param("endCount") Integer endCount, Pageable pageable);

    @Query("select * from Article where sn = :sn")
    Article findBySn(@Param("sn")String sn);
}
