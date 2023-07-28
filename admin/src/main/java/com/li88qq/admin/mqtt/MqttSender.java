package com.li88qq.admin.mqtt;

import org.springframework.integration.annotation.MessagingGateway;

/**
 * mqtt服务
 *
 * @author li88qq
 * @version 1.0 2023/7/27 22:55
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttSender {

    void sendToMqtt(String data);
}
