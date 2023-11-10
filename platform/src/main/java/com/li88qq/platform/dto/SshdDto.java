package com.li88qq.platform.dto;

/**
 * sshd连接配置
 *
 * @author li88qq
 * @version 1.0 2023/11/10 22:42
 */
public class SshdDto {

    private String host;//ip
    private int port = 22;//端口
    private String username;//用户名
    private String password;//密码
    private int timeout = 30;//超时时间,单位秒

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
