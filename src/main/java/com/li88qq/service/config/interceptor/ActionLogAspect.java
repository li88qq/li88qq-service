package com.li88qq.service.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.li88qq.service.constant.annitions.AcLog;
import com.li88qq.service.entity.ActionLog;
import com.li88qq.service.repo.ActionLogRepo;
import com.li88qq.service.utils.SessionUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作日记aop
 *
 * @author li88qq
 * @version 1.0 2021/8/12 22:19
 */
@Aspect
@Component
public class ActionLogAspect {

    @Resource
    private ActionLogRepo actionLogRepo;

    @Pointcut(value = "@annotation(acLog)", argNames = "acLog")
    public void pointcut(AcLog acLog) {
    }

    @After(value = "pointcut(acLog)", argNames = "joinPoint,acLog")
    public void after(JoinPoint joinPoint, AcLog acLog) {
        //处理数据内容
        String detail = acLog.detail();
        String _detail = "";
        if (detail != null && !detail.equals("")) {
            Map<String, Object> paramMap = handleParams(joinPoint);
            if (!paramMap.isEmpty()) {
                _detail = handleDetail(paramMap, detail);
            }
        }

        ActionLog actionLog = new ActionLog();
        actionLog.setUid(SessionUtil.getUid());
        actionLog.setIp(SessionUtil.getIp());
        actionLog.setAcType(acLog.acType().getType());
        actionLog.setTitle(acLog.title());
        actionLog.setDetail(_detail);
        actionLogRepo.save(actionLog);
    }

    //参数map
    private Map<String, Object> handleParams(JoinPoint joinPoint) {
        Map<String, Object> paramMap = new HashMap<>();
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return paramMap;
        }
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Parameter[] parameters = method.getParameters();
        int index = 0;
        for (Parameter parameter : parameters) {
            paramMap.put(parameter.getName(), args[index++]);
        }
        return paramMap;
    }

    //处理日记内容
    //detail格式:bo|id,name;id;
    //格式:多个方法参数使用分号;隔开,每个参数以参数名开头,如果是对象,取其中参数,使用|标志,每个使用逗号隔开
    private String handleDetail(Map<String, Object> paramMap, String detail) {
        Map<String, String> detailMap = new HashMap<>();
        String[] arr = detail.split(";");
        for (String key : arr) {
            if (key.equals("")) {
                continue;
            }
            String[] v = key.split("\\|");
            detailMap.put(v[0], v.length > 1 ? v[1] : null);
        }
        if (detailMap.isEmpty()) {
            return "";
        }

        Object value = null;
        String keys = null;
        String jsonString = null;
        Map<String, Object> tMap = null;
        JSONObject jsonObject = null;
        Map<String, Object> logMap = new HashMap<>();
        for (String key : detailMap.keySet()) {
            value = paramMap.get(key);
            if (value == null) {
                continue;
            }
            keys = detailMap.get(key);
            if (keys == null) {//整个对象
                logMap.put(key, value);
                continue;
            }
            jsonString = JSON.toJSONString(value);
            if (!jsonString.startsWith("{")) {//非对象
                logMap.put(key, value);
                continue;
            }
            tMap = new HashMap<>();
            jsonObject = JSON.parseObject(jsonString);
            for (String k : keys.split(",")) {
                tMap.put(k, jsonObject.get(k));
            }
            logMap.put(key, tMap);
        }
        return JSON.toJSONString(logMap);
    }
}
