package com.li88qq.service.repo;

import com.li88qq.service.entity.ToDo;
import com.li88qq.service.response.ToDoVo;
import org.fastquery.core.Modifying;
import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;
import org.fastquery.page.Page;
import org.fastquery.page.Pageable;
import org.fastquery.where.Condition;

import java.util.List;

/**
 * @author li88qq
 * @version 1.0 2021/9/2 22:58
 */
public interface ToDoRepo extends QueryRepository {

    /**
     * 根据id查询
     *
     * @param uid
     * @param id
     * @return
     */
    @Query("select * from ToDo where id = :id and uid = :uid")
    ToDo findById(@Param("uid") Long uid, @Param("id") Long id);

    /**
     * 根据id删除
     *
     * @param id
     */
    @Modifying
    @Query("delete from ToDo where id = :id")
    void deleteById(@Param("id") Long id);

    /**
     * 查询列表
     *
     * @param uid
     * @param labelId
     * @return
     */
    @Query("select t.id,t.content,t.beginDate,t.endDate,t.sort,t.createDate,label.id labelId,label.name labelName from ToDo t " +
            "left join ToDoLabel label on t.labelId = label.id #{#where} order by t.sort desc,t.endDate asc")
    @Condition("t.uid = :uid")
    @Condition(" and t.state = 0")
    @Condition(" and t.labelId = :labelId")
    List<ToDoVo> findList(@Param("uid") Long uid, @Param("labelId") Long labelId);

    /**
     * 分页查询列表
     *
     * @param uid
     * @param labelId
     * @param pageable
     * @return
     */
    /**
     * 查询列表
     *
     * @param uid
     * @param labelId
     * @return
     */
    @Query(value = "select t.id,t.content,t.beginDate,t.endDate,t.sort,t.createDate,t.finishDate,label.id labelId,label.name labelName from ToDo t " +
            "left join ToDoLabel label on t.labelId = label.id #{#where} order by t.sort desc,t.endDate asc", countField = "t.id")
    @Condition("t.uid = :uid")
    @Condition(" and t.state = 1")
    @Condition(" and t.labelId = :labelId")
    Page<ToDoVo> findPage(@Param("uid") Long uid, @Param("labelId") Long labelId, Pageable pageable);
}
