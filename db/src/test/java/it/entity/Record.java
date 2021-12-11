package it.entity;

import com.li88qq.db.annotation.Id;
import com.li88qq.db.annotation.Table;

/**
 * 记录
 *
 * @author li88qq
 * @version 1.0 2021/12/10 23:42
 */
@Table(name = "Record")
public class Record {

    @Id
    private Long id;
    private String name;
    private Long createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
