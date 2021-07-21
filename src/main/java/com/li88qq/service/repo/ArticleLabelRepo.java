package com.li88qq.service.repo;

import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;

import java.util.List;
import java.util.Set;

public interface ArticleLabelRepo extends QueryRepository {

    @Query("select id from ArticleLabel where name in (:names)")
    List<Long> findIdByNames(@Param("names") Set<String> names);
}
