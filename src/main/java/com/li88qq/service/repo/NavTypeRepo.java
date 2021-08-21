package com.li88qq.service.repo;

import com.li88qq.service.entity.NavigationType;
import org.fastquery.core.Modifying;
import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;

import java.util.List;

/**
 * @author li88qq
 * @version 1.0 2021/8/21 10:30
 */
public interface NavTypeRepo extends QueryRepository {

    @Query("select * from NavigationType where uid = :uid order by sort asc")
    List<NavigationType> findList(@Param("uid") Long uid);

    @Modifying
    @Query("update NavigationType set name = :name where id = :id and uid = :uid")
    void updateName(@Param("uid") Long uid, @Param("id") Long id, @Param("name") String name);

    @Modifying
    @Query("delete from NavigationType where id = :id and uid = :uid")
    void deleteById(@Param("uid") Long uid, @Param("id") Long id);
}
