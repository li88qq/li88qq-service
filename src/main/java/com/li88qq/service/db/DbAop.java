package com.li88qq.service.db;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 事务拦截器
 *
 * @author li88qq
 * @version 1.0 2021/11/10 23:57
 */
@Aspect
@Component
public class DbAop {

    @Resource
    private DataSource dataSource;

    @Pointcut(value = "@annotation(transactional)", argNames = "transactional")
    public void pointcut(Transactional transactional) {
    }

    /**
     * 事务拦截处理
     *
     * @param joinPoint
     * @param transactional
     * @return
     * @throws Throwable
     */
    @Around(value = "pointcut(transactional)", argNames = "joinPoint,transactional")
    public Object after(ProceedingJoinPoint joinPoint, Transactional transactional) throws Throwable {
        Connection connection = null;
        try {
            connection = ThreadLocalUtil.get();
            if (connection == null) {
                connection = dataSource.getConnection();
            }
            connection.setAutoCommit(false);//设置不自动提交

            ThreadLocalUtil.set(connection);//注入

            Object proceed = joinPoint.proceed();//执行

            connection.commit();//自动提交
            return proceed;
        } catch (Exception e) {
            connection.rollback();//回滚
            throw e;
        } finally {
            ThreadLocalUtil.remove();//移除
            connection.close();//关闭
        }
    }
}
