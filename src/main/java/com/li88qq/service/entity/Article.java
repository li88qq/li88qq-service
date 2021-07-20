package com.li88qq.service.entity;

import com.li88qq.service.utils.DateUtil;
import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 文章
 */
@Table("Article")
public class Article {

    @Id
    private Long id;
    private Long uid = 0L;//用户id
    private String title;//标题
    private Integer state = 0;//状态
    private Integer open = 0;//是否公开,1-公开
    private Integer original = 0;//是否原创,1-原创
    private String quote;//引用地址
    private Long createDate = DateUtil.getTimestamp();//创建时间
    private Long updateDate = 0L;//更新时间
    private Long ctUpdateDate = 0L;//内容更新时间

    private String sn;//随机码
    private Integer words;//字数
    private Integer readCount = 0;//阅读数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getOriginal() {
        return original;
    }

    public void setOriginal(Integer original) {
        this.original = original;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
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

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getCtUpdateDate() {
        return ctUpdateDate;
    }

    public void setCtUpdateDate(Long ctUpdateDate) {
        this.ctUpdateDate = ctUpdateDate;
    }
}
