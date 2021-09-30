package com.li88qq.service.utils;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.li88qq.service.dto.BaseResponse;

import java.util.Map;

/**
 * 短信发送工具类
 *
 * @author li88qq
 * @version 1.0 2021/9/29 23:46
 */
public class SmsUtil {

    private static final String accessKeyId = "";
    private static final String accessKeySecret = "";
    private static final String endpoint = "dysmsapi.aliyuncs.com";
    private static final String signName = "";//短信签名名称
    private static final String templateCode = "";//短信模板ID

    /**
     * 初始化配置
     *
     * @return
     * @throws Exception
     */
    public static Client createClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret)
                .setEndpoint(endpoint);
        return new Client(config);
    }

    /**
     * 发送短信验证码
     *
     * @param mobile 手机号码
     * @param params 占位对应参数
     * @throws Exception
     */
    public static BaseResponse send(String mobile, Map<String, Object> params) throws Exception {
        SendSmsRequest request = new SendSmsRequest();
        String templateParam = JSON.toJSONString(params);

        request.setPhoneNumbers(mobile);//手机号码
        request.setSignName(signName);//短信签名
        request.setTemplateCode(templateCode);//模板id
        request.setTemplateParam(templateParam);//占位参数,json

        Client client = createClient();
        SendSmsResponse response = client.sendSms(request);
        SendSmsResponseBody body = response.getBody();
        String code = body.getCode();// OK表示成功

        if (code != null && code.equals("OK")) {
            return ResponseUtil.ok(body);
        }
        return ResponseUtil.error(JSON.toJSONString(body));
    }

}
