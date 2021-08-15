package com.li88qq.service.repo;

import org.fastquery.core.Modifying;
import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;

public interface Article_LabelRepo extends QueryRepository {

    @Modifying
    @Query("delete from Article_Label where articleId = :articleId")
    void deleteById(@Param("articleId") Long articleId);
}
