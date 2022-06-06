package com.li88qq.main.mqtt;

import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * mqtt网关
 *
 * @author li88qq
 * @version 1.0 2022/5/26 22:44
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    /**
     * 发送到默认主题
     *
     * @param data 信息
     */
    void sendToMqtt(String data);

    /**
     * 发送信息
     *
     * @param topic 主题
     * @param data  信息
     */
    void sendToMqtt(@Header(name = MqttHeaders.TOPIC) String topic, String data);

    /**
     * 发送信息
     *
     * @param topic 主题
     * @param qos   qos
     * @param data  信息
     */
    void sendToMqtt(@Header(name = MqttHeaders.TOPIC) String topic, @Header(name = MqttHeaders.QOS) int qos, String data);

    /**
     * 发送信息
     *
     * @param topic   主题
     * @param message 信息
     */
    void sendToMqtt(@Header(name = MqttHeaders.TOPIC) String topic, MqttMessage message);

}
