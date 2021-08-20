package com.li88qq.service.entity;

import com.li88qq.service.utils.DateUtil;
import org.fastquery.core.Id;
import org.fastquery.core.Table;

/**
 * 导航类型
 *
 * @author li88qq
 * @version 1.0 2021/8/20 23:36
 */
@Table("NavigationType")
public class NavigationType {

    @Id
    private Long id;
    private Long uid = 0L;
    private String name;

    private Long createDate = DateUtil.getTimestamp();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
}
