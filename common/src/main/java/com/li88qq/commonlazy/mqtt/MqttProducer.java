package com.li88qq.commonlazy.mqtt;

import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.springframework.integration.mqtt.outbound.Mqttv5PahoMessageHandler;

/**
 * mqtt生产者
 *
 * @author li88qq
 * @version 1.0 2023/7/29 23:32
 */
public class MqttProducer extends Mqttv5PahoMessageHandler {

    public MqttProducer(String url, String clientId) {
        super(url, clientId);
    }

    public MqttProducer(MqttConnectionOptions connectionOptions, String clientId) {
        super(connectionOptions, clientId);
    }


}
