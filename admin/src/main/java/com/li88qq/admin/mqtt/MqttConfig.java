package com.li88qq.admin.mqtt;

import com.li88qq.common.utils.UUIDUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.Mqttv5PahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.Mqttv5PahoMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.converter.StringMessageConverter;

/**
 * mqtt
 *
 * @author li88qq
 * @version 1.0 2023/7/27 22:28
 */
@Configuration
public class MqttConfig {

    private static final Logger LOG = LogManager.getLogger();
    String server = "tcp://127.0.0.1:1883";


    /**
     * 发送端
     *
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        String[] serverUris = new String[]{server};
        MqttConnectionOptions options = new MqttConnectionOptions();
        options.setConnectionTimeout(10000);
        options.setServerURIs(serverUris);

        Mqttv5PahoMessageHandler messageHandler = new Mqttv5PahoMessageHandler(options, UUIDUtil.uuid8());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("topic1");
        return messageHandler;
    }

    /**
     * 发送channel
     *
     * @return
     */
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /**
     * 接受channel
     *
     * @return
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * 接收端
     *
     * @return
     */
    @Bean
    public MessageProducer inbound() {
        Mqttv5PahoMessageDrivenChannelAdapter adapter =
                new Mqttv5PahoMessageDrivenChannelAdapter(server, "testClient", "topic1", "topic2");
        adapter.setCompletionTimeout(50000);
        adapter.setMessageConverter(new StringMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * 接收端消息处理
     *
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                byte[] msg = (byte[]) message.getPayload();
                System.out.println(new String(msg));
            }
        };

    }

}
