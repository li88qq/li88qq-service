package com.li88qq.service.response;

public class GetAllPageVo {

    private Long id;
    private Long uid;
    private String username;
    private String title;//标题
    private Integer original;//是否原创,1-原创
    private String quote;//引用地址
    private Long createDate;//创建时间
    private Long updateDate;//更新时间
    private Long ctUpdateDate;//内容更新时间

    private String sn;//随机码
    private Integer words;//字数
    private Integer readCount;//阅读数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getCtUpdateDate() {
        return ctUpdateDate;
    }

    public void setCtUpdateDate(Long ctUpdateDate) {
        this.ctUpdateDate = ctUpdateDate;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}