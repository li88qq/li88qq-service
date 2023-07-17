package com.li88qq.db.aspect;

import com.li88qq.db.dao.MapperTemplate;
import com.li88qq.db.dto.page.Page;
import com.li88qq.db.dto.page.PageIdDto;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.dto.threadlocal.PageIdThreadLocal;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

/**
 * PageId拦截
 *
 * @author li88qq
 * @version 1.0 2023/3/3 23:11
 */
@Aspect
@Component
public class PageIdAspect {

    @Resource
    private MapperTemplate mapperTemplate;

    @Around(value = "@annotation(com.li88qq.db.annotion.PageId)")
    public Object aop(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object proceed = joinPoint.proceed();
            if (proceed instanceof Page) {
                proceed = handlePage(proceed);
            }
            return proceed;
        } finally {
            PageIdThreadLocal.remove();
        }
    }

    /**
     * 查询总数
     *
     * @param proceed 列表
     * @return 分页结果
     */
    private Page<?> handlePage(Object proceed) {
        //封装page对象
        PageIdDto pageIdDto = PageIdThreadLocal.get();
        String sql = pageIdDto.getSql();
        Pageable pageable = pageIdDto.getPageable();
        Map<String, Object> paramMap = pageIdDto.getParamMap();

        long count = 0L;
        // 是否包括groupBy,如果是,则取返回数据的记录数
        boolean groupBy = pageIdDto.isGroupBy();
        if (groupBy) {
            count = mapperTemplate.queryCountGroupBy(sql, paramMap).size();
        } else {
            count = mapperTemplate.queryCount(sql, paramMap);
        }

        ArrayList<?> list = (ArrayList<?>) proceed;
        Page<?> page = Page.convert(list);
        page.setTotal(count);
        page.setPage(pageable.getPage());
        page.setPageSize(pageable.getPageSize());
        return page;
    }
}
