package com.li88qq.admin.module.system.admin.dto.config;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 批量设置
 *
 * @author li88qq
 * @version 1.0 2023/7/23 16:52
 */
public class BatchUpdateForm {

    private List<Integer> ids;

    private MultipartFile icoFile;//小图标
    private MultipartFile logoFile;//logo
    private MultipartFile beiAnIcoFile;//备案图标

    private String beiAn;//备案
    private String beiAnLink;//备案链接
    private String icp;//icp
    private String icpLink;//icp链接
    private String copyRight;//版权声明

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

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
