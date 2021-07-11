package com.li88qq.service.config.bean;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha defaultKaptcha() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.textproducer.char.length", "4");//长度
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");//字体
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCEFGHJKLMNPQRSUVWXYZabcdefghjkmnpqrstuvwxyz");//字符,大写少DIOT,小写少iol
//        properties.setProperty("kaptcha.textproducer.font.color", "");//字体颜色
//        properties.setProperty("kaptcha.noise.color", "blue");//干扰颜色
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
