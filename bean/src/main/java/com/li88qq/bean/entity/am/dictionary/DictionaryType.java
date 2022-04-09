package com.li88qq.bean.entity.am.dictionary;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;
import com.li88qq.utils.DateUtil;

/**
 * 字典类型
 *
 * @author li88qq
 * @version 1.0 2022/4/2 23:15
 */
@Table
public class DictionaryType {

    @Id
    private Integer id;
    private String name;//字典名称
    private String remark;//描述
    private String tips;//帮助信息
    private Long createDate = DateUtil.getTimestamp();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
