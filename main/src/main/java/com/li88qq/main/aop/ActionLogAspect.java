package com.li88qq.main.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.li88qq.bean.annotion.AcLog;
import com.li88qq.bean.entity.system.ActionLog;
import com.li88qq.bean.web.session.SessionUtil;
import com.li88qq.bean.web.session.UserToken;
import com.li88qq.db.core.BaseMapper;
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
    private BaseMapper baseMapper;

    @Pointcut(value = "@annotation(acLog)", argNames = "acLog")
    public void pointcut(AcLog acLog) {
    }

    @After(value = "pointcut(acLog)", argNames = "joinPoint,acLog")
    public void after(JoinPoint joinPoint, AcLog acLog) {
        //处理数据内容
        String detail = acLog.data();
        if (detail != null && !detail.equals("")) {
            Object[] args = joinPoint.getArgs();
            try {
                detail = handleDetail(joinPoint, detail, args);
                if (detail.length() > 255) {
                    detail = detail.substring(0, 255);
                }
            } catch (Exception e) {
                detail = "";
            }
        }

        UserToken userToken = SessionUtil.getSession();
        Long uid = userToken.getUid();

        ActionLog actionLog = new ActionLog();

        actionLog.setUid(uid);
        actionLog.setIp(SessionUtil.getIp());
        actionLog.setActionType(acLog.actionType().getType());
        actionLog.setTitle(acLog.title());
        actionLog.setRemark(detail);
        baseMapper.save(actionLog);
    }

    //处理日记内容
    //detail格式:bo|id,name;id;
    //格式:多个方法参数使用分号;隔开,每个参数以参数名开头,如果是对象,取其中参数,使用|标志,每个使用逗号隔开
    private String handleDetail(JoinPoint joinPoint, String detail, Object[] args) {
        if (args.length == 0) {
            return "";
        }
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

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Parameter[] parameters = method.getParameters();
        int index = 0;

        String name = null;
        String keys = null;
        Map<String, Object> logMap = new HashMap<>();
        Map<String, Object> tMap = null;
        JSONObject jsonObject = null;
        Object value = null;
        String jsonString = null;
        for (Parameter parameter : parameters) {
            value = args[index];
            if (value == null) {
                index++;
                continue;
            }
            name = parameter.getName();
            if (detailMap.containsKey(name)) {
                keys = detailMap.get(name);
                if (keys == null) {
                    logMap.put(name, value);
                    index++;
                    continue;
                }

                jsonString = JSON.toJSONString(value);
                if (!jsonString.startsWith("{")) {
                    logMap.put(name, value);
                    index++;
                    continue;
                }

                tMap = new HashMap<>();
                jsonObject = JSON.parseObject(jsonString);
                for (String k : keys.split(",")) {
                    tMap.put(k, jsonObject.get(k));
                }
                logMap.put(name, tMap);
            }
            index++;
        }

        return JSON.toJSONString(logMap);
    }

    //用表达式获取数据
    private String handleDetail_SpEL(JoinPoint joinPoint, String detail, Object[] args) {
        return null;
    }
}
