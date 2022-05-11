package com.li88qq.main.dto.article;

/**
 * 保存文章
 *
 * @author li88qq
 * @version 1.0 2022/5/11 23:14
 */
public class SaveArticleForm {

    private String title;//标题
    private String img;//背景图
    private Integer open;//是否公开
    private Integer original;//是否原创
    private String transport;//转载地址

    private String doc;//文章内容
    private String originalDoc;//原内容

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getOriginalDoc() {
        return originalDoc;
    }

    public void setOriginalDoc(String originalDoc) {
        this.originalDoc = originalDoc;
    }
}
