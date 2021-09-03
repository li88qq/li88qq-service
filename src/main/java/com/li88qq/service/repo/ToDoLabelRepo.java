package com.li88qq.service.repo;

import com.li88qq.service.response.ToDoLabelVo;
import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;

import java.util.List;

/**
 * @author li88qq
 * @version 1.0 2021/9/2 22:14
 */
public interface ToDoLabelRepo extends QueryRepository {

    /**
     * 查询列表
     *
     * @param uid
     * @return
     */
    @Query("select id,name from ToDoLabel where uid = :uid and deleteState = 0")
    List<ToDoLabelVo> findList(@Param("uid") Long uid);
}
