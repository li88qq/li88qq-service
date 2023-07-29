package com.li88qq.commonlazy.mqtt;

import com.li88qq.common.utils.UUIDUtil;

/**
 * mqtt客户端生成
 *
 * @author li88qq
 * @version 1.0 2023/7/29 23:45
 */
public class MqttClientBuilder {

    /**
     * 生成客户端名称
     *
     * @param producer 是否生产者
     * @param appName  应用名称
     * @return 客户端id
     */
    public static String build(boolean producer, String appName) {
        String prefix = producer ? "P" : "C";
        String patten = "%s-%s-%s";
        return String.format(patten, prefix, patten, UUIDUtil.uuid8());
    }
}
