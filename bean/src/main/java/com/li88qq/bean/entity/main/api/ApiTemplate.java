package com.li88qq.bean.entity.main.api;

import com.li88qq.bean.entity.utils.CreateDate;
import com.li88qq.db.annotion.Id;

/**
 * api模板
 *
 * @author li88qq
 * @version 1.0 2023/12/10 22:48
 */
public class ApiTemplate {

    @Id
    private Long id;//自增id
    private Integer type = 0;//类型,1-公共,2-个人
    private Integer uid = 0;//用户id
    private String markdown;//markdown内容
    private String html;//html

    private Long createDate = CreateDate.now();//创建时间
    private Long updateDate = 0L;//最后编辑时间

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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
