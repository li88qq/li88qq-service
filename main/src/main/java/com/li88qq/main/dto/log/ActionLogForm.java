package com.li88qq.main.dto.log;

import com.li88qq.db.annotion.Like;
import com.li88qq.db.annotion.TimeMax;
import com.li88qq.db.annotion.TimeMin;
import com.li88qq.db.dto.PageForm;

import java.time.LocalDate;

/**
 * 操作记录
 *
 * @author li88qq
 * @version 1.0 2022/9/18 23:08
 */
public class ActionLogForm extends PageForm {

    private Integer actionType;
    @Like
    private String title;
    @Like
    private String remark;
    @TimeMin
    private LocalDate beginDate;
    @TimeMax
    private LocalDate endDate;
    @Like
    private String ip;//ip

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
