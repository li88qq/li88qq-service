package com.li88qq.main.mqtt;

import com.li88qq.utils.UUIDUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.inbound.Mqttv5PahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.Mqttv5PahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.nio.charset.StandardCharsets;

/**
 * mqtt配置
 *
 * @author li88qq
 * @version 1.0 2022/5/25 23:27
 */
@Configuration
public class MqttConfig {

    private static final Logger LOG = LogManager.getLogger();

    @Value("${mqtt.username}")
    private String username;
    @Value("${mqtt.password}")
    private String password = "public";
    @Value("${mqtt.server}")
    private String server;

    /**
     * 连接配置
     */
    @Bean
    public MqttConnectionOptions mqttConnectionOptions() {
        String[] servers = new String[]{"tcp://" + server};
        MqttConnectionOptions options = new MqttConnectionOptions();
        options.setUserName(username);
        options.setPassword(password.getBytes(StandardCharsets.UTF_8));
        options.setAutomaticReconnect(true);
        options.setServerURIs(servers);
        return options;
    }

    /**
     * 发送通道
     */
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /**
     * 发送端
     *
     * @param mqttConnectionOptions 连接配置
     * @return 发送端
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public Mqttv5PahoMessageHandler sender(MqttConnectionOptions mqttConnectionOptions) {
        String clientId = "main_server_" + UUIDUtil.uuid8();
        Mqttv5PahoMessageHandler handler = new Mqttv5PahoMessageHandler(mqttConnectionOptions, clientId);
        handler.setAsync(true);
        handler.setDefaultTopic("topic1");
        LOG.info("mqtt发送端注册成功!");

        return handler;
    }

    /**
     * 接收通道
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * 接收端
     *
     * @param mqttConnectionOptions 连接配置
     * @param mqttInputChannel      返回通道
     * @return 接收端
     */
    @Bean
    public Mqttv5PahoMessageDrivenChannelAdapter receiver(MqttConnectionOptions mqttConnectionOptions, MessageChannel mqttInputChannel) {
        String clientId = "main_client_" + UUIDUtil.uuid8();
        Mqttv5PahoMessageDrivenChannelAdapter adapter = new Mqttv5PahoMessageDrivenChannelAdapter(mqttConnectionOptions, clientId, "topic1");
        adapter.setOutputChannel(mqttInputChannel);
        return adapter;
    }

    /**
     * 接收处理
     *
     * @return 接收端
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            byte[] payload = (byte[]) message.getPayload();
            String data = new String(payload);
            LOG.info(data);
        };
    }

}
