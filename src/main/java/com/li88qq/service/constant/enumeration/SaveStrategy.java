package com.li88qq.service.constant.enumeration;

/**
 * 操作记录保存策略
 *
 * @author li88qq
 * @version 1.0 2021/8/11 23:37
 */
public enum SaveStrategy {

    SUCCESS(1, "成功"),
    FAIL(2, "失败"),
    ALL(3, "全部"),
    NONE(-1, "不保存"),

    ;
    private Integer strategy;
    private String text;

    SaveStrategy(Integer strategy, String text) {
        this.strategy = strategy;
        this.text = text;
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
