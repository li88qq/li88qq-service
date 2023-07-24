package com.li88qq.admin.module.system.admin.dto.config;

import org.springframework.web.multipart.MultipartFile;

/**
 * 新增
 *
 * @author li88qq
 * @version 1.0 2023/7/23 16:52
 */
public class SaveForm {

    private String domain;//域名
    private String appName;//应用名称

    private MultipartFile icoFile;//小图标
    private MultipartFile logoFile;//logo
    private MultipartFile beiAnIcoFile;//备案图标

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

    public MultipartFile getIcoFile() {
        return icoFile;
    }

    public void setIcoFile(MultipartFile icoFile) {
        this.icoFile = icoFile;
    }

    public MultipartFile getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(MultipartFile logoFile) {
        this.logoFile = logoFile;
    }

    public MultipartFile getBeiAnIcoFile() {
        return beiAnIcoFile;
    }

    public void setBeiAnIcoFile(MultipartFile beiAnIcoFile) {
        this.beiAnIcoFile = beiAnIcoFile;
    }
}
