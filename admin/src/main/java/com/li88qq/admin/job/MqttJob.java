package com.li88qq.admin.job;

import com.li88qq.admin.mqtt.MqttSender;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * mqtt定时发送任务
 *
 * @author li88qq
 * @version 1.0 2023/7/27 23:03
 */
@Component
public class MqttJob {

    @Resource
    private MqttSender mqttSender;

    private static final Logger LOG = LogManager.getLogger();


    @Scheduled(cron = "*/5 * * * * ?")
    public void send(){
        String msg = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LOG.info("send:{}",msg);
        mqttSender.sendToMqtt(msg);
    }
}
