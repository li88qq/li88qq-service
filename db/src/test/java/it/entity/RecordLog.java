package it.entity;

import com.li88qq.db.annotation.Id;
import com.li88qq.db.annotation.Table;

/**
 * 记录关联表
 *
 * @author li88qq
 * @version 1.0 2021/12/10 23:43
 */
@Table
public class RecordLog {

    @Id
    private Long id;
    @Id
    private Long recordId;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
