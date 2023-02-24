package data;

import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;

/**
 * 用户表
 *
 * @author li88qq
 * @version 1.0 2023/2/24 22:52
 */
@Table
public class User {

    @Id
    private Long id;
    private String name;

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
}
