package com.li88qq.db.interceptor.plugins;

import com.li88qq.db.interceptor.chains.*;
import jakarta.annotation.Resource;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.stereotype.Component;


/**
 * StatementInterceptor
 *
 * @author li88qq
 * @version 1.0 2023/2/28 22:14
 */
@Component
public class StatementInterceptor {

    @Resource
    private PageIdChain pageIdChain;
    @Resource
    private InsertIdChain insertIdChain;
    @Resource
    private ConditionChain conditionChain;
    @Resource
    private DatabaseChain databaseChain;
    @Resource
    private DataSourceChain dataSourceChain;
    @Resource
    private MethodChain methodChain;
    @Resource
    private PageableChain pageableChain;

    /**
     * 执行
     */
    public Object invoke(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        //拦截链
        ChainManager chainManager = new ChainManager(handler);

        chainManager.add(methodChain);//方法拦截
        chainManager.add(dataSourceChain);//数据源
        chainManager.add(databaseChain);//数据库
        chainManager.add(insertIdChain);//InsertId保存
        chainManager.add(conditionChain);//动态条件
        chainManager.add(pageableChain);//pageable分页参数处理
        chainManager.add(pageIdChain);//PageId,需先处理Pageable

        chainManager.execute();//执行拦截

        return invocation.proceed();
    }

}
