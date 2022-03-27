package com.li88qq.login.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * 短信验证码工具
 *
 * @author li88qq
 * @version 1.0 2022/3/27 21:07
 */
@Component
public class SmsUtil {

    @Value("${sms.accessKeyId}")
    private String accessKeyId;
    @Value("${sms.accessKeySecret}")
    private String accessKeySecret;
    @Value("${sms.signName}")
    private String signName;
    @Value("${sms.templateCode}")
    private String templateCode;

    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId     id
     * @param accessKeySecret Secret
     * @return Client
     * @throws Exception
     */
    private Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

    /**
     * 发送验证码
     *
     * @param mobile 手机号码
     * @param code   验证码
     * @return 是否成功
     */
    public boolean send(String mobile, String code) {
        try {
            JSONObject param = new JSONObject();
            param.put("code", code);

            Client client = createClient(accessKeyId, accessKeySecret);
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(mobile);//接收的手机号码
            request.setSignName(signName);//签名模板
            request.setTemplateCode(templateCode);//短信模板
            request.setTemplateParam(param.toJSONString());//验证码
            // 复制代码运行请自行打印 API 的返回值
            client.sendSms(request);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 生成6位随机码
     */
    public String smsCode() {
        SecureRandom secureRandom = new SecureRandom();
        int value = secureRandom.nextInt(100000, 999999);
        return String.valueOf(value);
    }

}
