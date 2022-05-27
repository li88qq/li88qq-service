package com.li88qq.main.mqtt;

import com.li88qq.utils.UUIDUtil;

import java.util.Map;
import java.util.concurrent.*;

/**
 * mqtt同步发送
 *
 * @author li88qq
 * @version 1.0 2022/5/27 23:32
 */
public class MqttFuture extends CompletableFuture<String> {

    private static final Map<String, MqttFuture> FUTURE_MAP = new ConcurrentHashMap<>();

    private String id;//唯一id

    private MqttFuture() {
    }

    /**
     * 构建MqttFuture
     *
     * @return MqttFuture
     */
    public static MqttFuture build() {
        MqttFuture future = new MqttFuture();
        String uuid = UUIDUtil.uuid19();

        FUTURE_MAP.put(uuid, future);

        future.setId(uuid);
        return future;
    }

    /**
     * 完成
     *
     * @param id    消息id
     * @param resul 响应结果
     */
    public static void done(String id, String resul) {
        if (id != null) {
            MqttFuture mqttFuture = FUTURE_MAP.remove(id);
            if (mqttFuture != null && !mqttFuture.isDone()) {
                mqttFuture.complete(resul);
            }
        }
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }
}
