package com.li88qq.commonlazy.mqtt;

import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.springframework.integration.mqtt.inbound.Mqttv5PahoMessageDrivenChannelAdapter;

/**
 * mqtt消费者
 *
 * @author li88qq
 * @version 1.0 2023/7/29 23:33
 */
public class MqttConsumer extends Mqttv5PahoMessageDrivenChannelAdapter {

    public MqttConsumer(String url, String clientId, String... topic) {
        super(url, clientId, topic);
    }

    public MqttConsumer(MqttConnectionOptions connectionOptions, String clientId, String... topic) {
        super(connectionOptions, clientId, topic);
    }
}
