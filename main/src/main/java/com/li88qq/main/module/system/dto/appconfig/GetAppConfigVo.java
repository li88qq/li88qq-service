package com.li88qq.main.module.system.dto.appconfig;

import com.li88qq.bean.utils.CreateDate;

/**
 * 平台设置
 *
 * @author li88qq
 * @version 1.0 2024/1/20 10:43
 */
public class GetAppConfigVo {

    private Integer id;//自增id
    private String appCode;//应用编码
    private String appName;//应用名称
    private String domain;//域名
    private String ico;//小图标
    private String logo;//logo
    private String beiAnIco;//备案图标
    private String beiAn;//备案
    private String beiAnLink;//备案链接
    private String icp;//icp
    private String icpLink;//icp链接
    private String copyRight;//版权声明

    private Long createDate = CreateDate.now();//创建时间
    private Long updateDate = 0L;//更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBeiAnIco() {
        return beiAnIco;
    }

    public void setBeiAnIco(String beiAnIco) {
        this.beiAnIco = beiAnIco;
    }

    public String getBeiAn() {
        return beiAn;
    }

    public void setBeiAn(String beiAn) {
        this.beiAn = beiAn;
    }

    public String getBeiAnLink() {
        return beiAnLink;
    }

    public void setBeiAnLink(String beiAnLink) {
        this.beiAnLink = beiAnLink;
    }

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp;
    }

    public String getIcpLink() {
        return icpLink;
    }

    public void setIcpLink(String icpLink) {
        this.icpLink = icpLink;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
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
