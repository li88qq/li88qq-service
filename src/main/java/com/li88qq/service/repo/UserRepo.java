package com.li88qq.service.repo;

import com.li88qq.service.entity.User;
import org.fastquery.core.Param;
import org.fastquery.core.Query;
import org.fastquery.core.QueryRepository;

public interface UserRepo extends QueryRepository {

    @Query("select * from User where username = :username")
    User findByUsername(@Param("username") String username);
}
