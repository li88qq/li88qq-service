package com.li88qq.main.mqtt;

import com.alibaba.fastjson.JSON;
import com.li88qq.bean.dto.BaseResult;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 发送服务
 *
 * @author li88qq
 * @version 1.0 2022/5/28 22:51
 */
@Component
public class PushService {

    @Resource
    private MqttGateway mqttGateway;
    private static final String MSG_ID = "msgId";

    /**
     * 发送
     *
     * @param topic 主题
     * @param data  数据
     */
    public void send(String topic, String data) {
        mqttGateway.sendToMqtt(topic, data);
    }

    /**
     * 发送并响应信息
     *
     * @param topic   主题
     * @param data    数据
     * @param timeout 超时时间,单位秒
     * @return 响应结果
     */
    public BaseResult<String> sendAndReceive(String topic, String data, long timeout) {
        MqttMessage message = new MqttMessage();
        MqttFuture mqttFuture = MqttFuture.build();
        String id = mqttFuture.getId();
        try {
            //封装信息,用户信息
            MqttProperties properties = new MqttProperties();
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put(MSG_ID, id);
            properties.setCorrelationData(JSON.toJSONBytes(dataMap));
            message.setPayload(data.getBytes(StandardCharsets.UTF_8));

            mqttGateway.sendToMqtt(topic, message);

            String result = mqttFuture.get(timeout, TimeUnit.MILLISECONDS);
            return BaseResult.ok(result);
        } catch (Exception e) {
            return BaseResult.error("发送失败");
        } finally {
            MqttFuture.remove(id);
        }
    }
}
