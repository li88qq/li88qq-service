package com.li88qq.bean.beans.log;

import com.li88qq.bean.utils.CreateDate;
import com.li88qq.db.annotion.Id;

/**
 * 操作记录
 *
 * @author li88qq
 * @version 1.0 2023/10/11 22:41
 */
public class ActionLog {

    @Id
    private Long id;//自增id
    private Integer uid = 0;//用户id
    private Integer type = 0;//操作类型
    private String title;//标题
    private String detail;//详情
    private String coreData;//关键数据
    private Long createDate = CreateDate.now();//操作时间
    private String ip;//ip

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getCoreData() {
        return coreData;
    }

    public void setCoreData(String coreData) {
        this.coreData = coreData;
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
