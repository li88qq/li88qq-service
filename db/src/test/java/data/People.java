package data;

import com.li88qq.db.annotion.Column;
import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;

/**
 * @author li88qq
 * @version 1.0 2023/2/26 11:38
 */
@Table
public class People {

    @Id
    @Column("people_id")
    private Long pid;
    private String name;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
