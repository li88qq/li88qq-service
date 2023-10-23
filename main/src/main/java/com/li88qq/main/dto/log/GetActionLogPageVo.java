package com.li88qq.main.dto.log;

/**
 * 操作记录
 *
 * @author li88qq
 * @version 1.0 2023/10/17 23:01
 */
public class GetActionLogPageVo {

    private Long id;//自增id
    private Integer type;//操作类型
    private String title;//标题
    private String detail;//详情
    private Long createDate;//操作时间
    private String ip;//ip

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
