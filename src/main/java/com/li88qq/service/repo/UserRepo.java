package com.li88qq.service.repo;

import com.li88qq.service.entity.User;
import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;

public interface UserRepo extends QueryRepository {

    /**
     * 根据账号查询
     *
     * @param username
     * @return
     */
    @Query("select * from User where username = :username")
    User findByUsername(@Param("username") String username);

    /**
     * 根据手机号码查询
     *
     * @param mobile
     * @return
     */
    @Query("select * from User where mobile = :mobile")
    User findByMobile(@Param("mobild") String mobile);
}
