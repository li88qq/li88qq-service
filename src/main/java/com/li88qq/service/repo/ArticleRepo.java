package com.li88qq.service.repo;

import com.li88qq.service.entity.Article;
import org.fastquery.core.Modifying;
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
    @Condition(" and delState = 0")
    @Condition(" and state =:state")
    @Condition(" and open =:open")
    @Condition(" and original =:original")
    @Condition(" and createDate >= :beginDate")
    @Condition(" and createDate <= :endDate")
    @Condition(" and readCount >= :beginCount")
    @Condition(" and readCount <= :endCount")
    @Condition(" and title like :title")
    @Condition(" and labels like :labels")
    Page<Map<String, Object>> findPage(@Param("uid") Long uid, @Param("title") String title, @Param("labels") String labels, @Param("state") Integer state,
                                       @Param("open") Integer open, @Param("original") Integer original, @Param("beginDate") Long beginDate,
                                       @Param("endDate") Long endDate, @Param("beginCount") Integer beginCount, @Param("endCount") Integer endCount,
                                       Pageable pageable);

    @Query(value = "select a.*,u.nickname as username from Article a left join User u on a.uid = u.id #{#where} order by a.id desc", countField = "a.id")
    @Condition(" a.state =0 and a.open = 1 and a.delState = 0")
    @Condition(" and a.original =:original")
    @Condition(" and a.createDate >= :beginDate")
    @Condition(" and a.createDate <= :endDate")
    @Condition(" and a.readCount >= :beginCount")
    @Condition(" and a.readCount <= :endCount")
    @Condition(" and a.title like :title")
    @Condition(" and a.labels like :labels")
    @Condition(" and u.nickname like :username")
    Page<Map<String, Object>> findAllPage(@Param("title") String title, @Param("labels") String labels, @Param("username") String username,
                                          @Param("original") Integer original, @Param("beginDate") Long beginDate,
                                          @Param("endDate") Long endDate, @Param("beginCount") Integer beginCount,
                                          @Param("endCount") Integer endCount, Pageable pageable);

    @Query("select * from Article where sn = :sn and delState = 0")
    Article findBySn(@Param("sn") String sn);

    /**
     * 更新阅读次数
     *
     * @param id
     */
    @Modifying
    @Query("update Article set readCount = readCount+1 where id = :id")
    void updateReadCount(@Param("id") Long id);

    /**
     * 逻辑删除
     *
     * @param uid
     * @param id
     * @return
     */
    @Modifying
    @Query("update Article set delState = 1 where id = :id and uid = :uid")
    int deleteById(@Param("uid") Long uid, @Param("id") Long id);

    /**
     * 根据id查询
     *
     * @param uid
     * @param id
     * @return
     */
    @Query("select * from Article where id = :id and uid = :uid and delState = 0")
    Article findById(@Param("uid") Long uid, @Param("id") Long id);

    /**
     * 跟新文章内容修改时间
     *
     * @param id
     * @param ct
     */
    @Modifying
    @Query("update Article set ctUpdateDate = :ct,words = :words where id = :id")
    void updateCt(@Param("id") Long id, @Param("ct") Long ct, @Param("words") Integer words);
}
