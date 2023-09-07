package com.li88qq.db.interceptor.chains;

import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.page.PageIdDto;
import com.li88qq.db.dto.page.Pageable;
import com.li88qq.db.dto.threadlocal.PageIdThreadLocal;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PageId声明分页处理
 *
 * @author li88qq
 * @version 1.0 2023/3/1 22:03
 */
@Component
public class PageIdChain implements InterceptorChain {

    //参数占位符
    private static final String PLACE = "\\?";

    @Override
    public boolean execute(StatementHandler handler, Method method) {
        //是否有PageId
        PageId pageId = method.getDeclaredAnnotation(PageId.class);
        if (pageId == null) {
            return true;
        }
        BoundSql boundSql = handler.getBoundSql();
        Pageable pageable = getPageable(boundSql);
        PageIdDto pageIdDto = build(boundSql, pageId, pageable);
        pageIdDto.setGroupBy(pageIdDto.isGroupBy());

        PageIdThreadLocal.set(pageIdDto);

        return true;
    }

    /**
     * 构建 PageIdDto
     *
     * @param boundSql BoundSql
     * @param pageId   PageId
     * @param pageable Pageable
     * @return PageIdDto
     */
    public PageIdDto build(BoundSql boundSql, PageId pageId, Pageable pageable) {
        //1.构建sql, select count(id) from abc,需要去掉limit后面语句
        String basicSql = buildSql(boundSql, pageId);
        //2.获取参数列表,最终转个一个公共的参数Map
        List<Map<String, Object>> paramList = buildParamList(boundSql, basicSql);

        PageIdDto pageIdDto = new PageIdDto();
        //如果没参数，直接返回
        if (paramList.isEmpty()) {
            pageIdDto.setSql(basicSql);
            pageIdDto.setParamMap(new HashMap<>());
            pageIdDto.setPageable(pageable);
            pageIdDto.setGroupBy(pageIdDto.isGroupBy());
            return pageIdDto;
        }

        //3.参数合并成map,@Param("map")Map<String,Object>map
        //处理逻辑:1.占位符? 2.找到对应key为a.b 3.由a.b转变为map.a_b
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String[] sqlArr = basicSql.split(PLACE);
        String format = "%s#{map.%s}";
        String key = null;
        String formKey = null;
        Map<String, Object> temp = null;
        for (int i = 0; i < paramList.size(); i++) {
            temp = paramList.get(i);

            key = String.valueOf(temp.get("key"));
            formKey = key.replace(".", "_");

            paramMap.put(formKey, temp.get("value"));
            sb.append(String.format(format, sqlArr[i], formKey));
        }
        if (sqlArr.length == paramList.size() + 1) {
            sb.append(sqlArr[sqlArr.length - 1]);
        }

        pageIdDto.setSql(sb.toString());
        pageIdDto.setParamMap(paramMap);
        pageIdDto.setPageable(pageable);
        return pageIdDto;
    }

    //构建sql
    private String buildSql(BoundSql boundSql, PageId pageId) {
        String sql = boundSql.getSql();

        //处理统计字段
        String countField = "id";
        String countField1 = pageId.countField();
        if (countField1 != null && !countField1.equals("")) {
            countField = countField1;
        }

        //如果有limit,判断是否有?
        int limitIndex = sql.lastIndexOf("limit");
        if (limitIndex != -1) {
            sql = sql.substring(0, limitIndex);
        }

        //第一个from
        int fromIndex = sql.indexOf("from");
        if (fromIndex == -1) {
            fromIndex = sql.indexOf("FROM");
        }

        String sqlFormat = "select count(%s) %s";
        return String.format(sqlFormat, countField, sql.substring(fromIndex));
    }

    //构建参数
    private List<Map<String, Object>> buildParamList(BoundSql boundSql, String sql) {
        //如果参数为空，或少于2个，直接返回,因为默认分页参数是带有limit条件,2个参数的
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings == null || parameterMappings.size() <= 2) {
            return new ArrayList<>();
        }
        Object parameterObject = boundSql.getParameterObject();
        MetaObject paramMetaObject = SystemMetaObject.forObject(parameterObject);

        List<Map<String, Object>> paramList = new ArrayList<>();
        String key;
        Object value;

        //正则搜索
        int index = 0;
        Pattern compile = Pattern.compile(PLACE);
        Matcher matcher = compile.matcher(sql);
        Map<String, Object> paramMap = null;
        while (matcher.find()) {
            key = parameterMappings.get(index).getProperty();
            if (!key.startsWith(ForEachSqlNode.ITEM_PREFIX)) {
                value = paramMetaObject.getValue(key);
            } else {
                value = boundSql.getAdditionalParameter(key);
            }

            paramMap = new HashMap<>();
            paramMap.put("key", key);
            paramMap.put("value", value);
            paramList.add(paramMap);
            index++;
        }
        return paramList;
    }

    /**
     * 获取pageable
     *
     * @return Pageable
     */
    private Pageable getPageable(BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        if (parameterObject instanceof Pageable) {
            return (Pageable) parameterObject;
        }

        Pageable pageable = null;
        MetaObject paramMetaObject = SystemMetaObject.forObject(parameterObject);
        String[] getterNames = paramMetaObject.getGetterNames();
        for (String name : getterNames) {
            Object value = paramMetaObject.getValue(name);
            if (value instanceof Pageable) {
                pageable = (Pageable) value;
            }
        }

        return pageable;
    }
}
