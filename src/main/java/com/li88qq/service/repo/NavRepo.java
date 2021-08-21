package com.li88qq.service.repo;

import com.li88qq.service.entity.Navigation;
import org.fastquery.core.Modifying;
import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;
import org.fastquery.page.Page;
import org.fastquery.page.Pageable;
import org.fastquery.where.Condition;

import java.util.List;
import java.util.Map;

/**
 * @author li88qq
 * @version 1.0 2021/8/21 9:50
 */
public interface NavRepo extends QueryRepository {

    /**
     * 分页查询
     */
    @Query("select n.id,n.name,n.url,n.remark,n.createDate,n.clickCount,t.id typeId,t.name typeName from Navigation n " +
            "left join NavigationType t on n.typeId = t.id #{#where} order by id desc")
    @Condition("n.uid = :uid")
    @Condition(" and n.typeId = :typeId")
    @Condition(" and n.name like :name")
    @Condition(" and n.url like :url")
    @Condition(" and n.remark like :remark")
    Page<Map<String, Object>> findPage(@Param("uid") Long uid, @Param("typeId") Long typeId, @Param("name") String name,
                                       @Param("url") String url, @Param("remark") String remark, Pageable pageable);

    /**
     * 批量删除
     *
     * @param uid
     * @param ids
     */
    @Modifying
    @Query("delete from Navigation where id in (:ids) and uid = :uid")
    void deleteByIds(@Param("uid") Long uid, @Param("ids") List<Long> ids);

    /**
     * 增加点击数
     *
     * @param uid
     * @param id
     */
    @Modifying
    @Query("update Navigation set clickCount = clickCount + 1 where id = :id and uid = :uid")
    void updateCount(@Param("uid") Long uid, @Param("id") Long id);

    /**
     * 查询列表
     *
     * @param uid
     * @param key
     * @return
     */
    @Query("select n.id, n.name,n.url,n.remark,t.id typeId,t.name typeName from Navigation n " +
            "left join NavigationType t on n.typeId = t.id #{#where} order by t.sort asc,n.clickCount desc")
    @Condition("n.uid = :uid")
    @Condition(" and (n.name like :key or n.url like :key or n.remark like :key or t.name like :key)")
    List<Map<String, Object>> findList(@Param("uid") Long uid, @Param("key") String key);

    @Modifying
    @Query("delete from Navigation where typeId = :id and uid =:uid")
    void deleteByType(@Param("uid") Long uid, @Param("id") Long id);
}
