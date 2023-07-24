package com.li88qq.admin.module.system.admin.dto.config;

/**
 * 查询
 *
 * @author li88qq
 * @version 1.0 2023/7/23 17:13
 */
public class GetVo {

    private String domain;//域名
    private String appName;//应用名称
    private String ico;//小图标
    private String logo;//logo
    private String beiAnIco;//备案图标
    private String beiAn;//备案
    private String beiAnLink;//备案链接
    private String icp;//icp
    private String icpLink;//icp链接
    private String copyRight;//版权声明

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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
}
