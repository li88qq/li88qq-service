package com.li88qq.service.repo;

import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;

import java.util.List;

public interface ArticleLabelRepo extends QueryRepository {

    @Query("select id from ArticleLabel where name in (:names)")
    List<Long> findIdByNames(@Param("names") List<String> names);
}
