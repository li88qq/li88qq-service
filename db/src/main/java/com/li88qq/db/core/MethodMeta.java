package com.li88qq.db.core;

import com.li88qq.db.annotion.Condition;
import com.li88qq.db.annotion.Conditions;
import com.li88qq.db.annotion.Format;
import com.li88qq.db.annotion.PageId;
import com.li88qq.db.dto.Page;
import com.li88qq.db.dto.Pageable;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * method元数据
 *
 * @author li88qq
 * @version 1.0 2022/3/12 22:24
 */
public class MethodMeta {

    private String className;//类名称
    private String methodName;//方法名称
    private Condition[] conditions;//条件语句
    private String pageable;//分页参数
    private PageId pageId;//分页注解
    private boolean queryPage;//是否分页查询
    private String[] formatObject;//需要格式化的对象,在查询时,对象使用@Format注解

    public static class Builder {
        private MethodMeta methodMeta;
        private final String id;

        public Builder(String id) {
            this.id = id;
        }

        /**
         * 根据id获取method
         *
         * @param id id
         * @return 方法
         */
        private Method getMethod(String id) {
            try {
                int index = id.lastIndexOf(".");
                String className = id.substring(0, index);
                String methodName = id.substring(index + 1);
                Class<?> aClass = Class.forName(className);
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    if (method.getName().equals(methodName)) {
                        return method;
                    }
                }
                throw new RuntimeException("方法不存在");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * 构建methodMeta
         *
         * @param method 方法
         */
        private void buildMeta(Method method) {
            methodMeta = new MethodMeta();
            Condition[] conditionList = null;
            Conditions conditions = method.getAnnotation(Conditions.class);
            if (conditions == null) {
                Condition condition = method.getAnnotation(Condition.class);
                if (condition != null) {
                    conditionList = new Condition[]{condition};
                }
            } else {
                conditionList = conditions.value();
            }

            PageId pageId = method.getAnnotation(PageId.class);
            String pageable = null;
            boolean queryPage = false;

            //判断pageable参数名称
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                if (parameter.getType() == Pageable.class) {
                    pageable = parameter.getName();
                    Param param = parameter.getAnnotation(Param.class);
                    if (param != null && param.value() != null) {
                        pageable = param.value();
                    }
                    break;
                }
            }

            //是否分页查询
            if (pageable != null) {
                if (method.getReturnType().equals(Page.class)) {
                    queryPage = true;
                }
            }

            //判断是否需要格式化
            List<String> formatKeys = new ArrayList<>();
            for (Parameter parameter : parameters) {
                if (parameter.isAnnotationPresent(Format.class)) {
                    Param param = parameter.getAnnotation(Param.class);
                    if (param != null) {
                        formatKeys.add(param.value());
                    } else {
                        formatKeys.add(parameter.getName());
                    }
                }
            }
            String[] formatObject = null;
            if (!formatKeys.isEmpty()) {
                formatObject = formatKeys.toArray(new String[0]);
            }

            methodMeta.setClassName(method.getDeclaringClass().getName());
            methodMeta.setMethodName(method.getName());
            methodMeta.setConditions(conditionList);
            methodMeta.setPageId(pageId);
            methodMeta.setPageable(pageable);
            methodMeta.setQueryPage(queryPage);
            methodMeta.setFormatObject(formatObject);
        }

        /**
         * 构建methodMeta
         */
        public MethodMeta build() {
            Method method = getMethod(id);
            buildMeta(method);
            return methodMeta;
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Condition[] getConditions() {
        return conditions;
    }

    public void setConditions(Condition[] conditions) {
        this.conditions = conditions;
    }

    public String getPageable() {
        return pageable;
    }

    public void setPageable(String pageable) {
        this.pageable = pageable;
    }

    public PageId getPageId() {
        return pageId;
    }

    public void setPageId(PageId pageId) {
        this.pageId = pageId;
    }

    public boolean isQueryPage() {
        return queryPage;
    }

    public void setQueryPage(boolean queryPage) {
        this.queryPage = queryPage;
    }

    public String[] getFormatObject() {
        return formatObject;
    }

    public void setFormatObject(String[] formatObject) {
        this.formatObject = formatObject;
    }
}
