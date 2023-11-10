package com.li88qq.platform.dto;

/**
 * cmd响应结果
 *
 * @author li88qq
 * @version 1.0 2023/11/10 22:41
 */
public class CmdResult {

    private String out;
    private String error;

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
