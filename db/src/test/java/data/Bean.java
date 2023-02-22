package data;

import com.li88qq.db.annotion.Column;
import com.li88qq.db.annotion.Id;
import com.li88qq.db.annotion.Table;
import com.li88qq.db.annotion.Transient;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 测试类
 *
 * @author li88qq
 * @version 1.0 2023/2/22 23:08
 */
@Table("Bean_Table")
public class Bean {

    @Id
    private Long id;

    @Column("bean_name")
    private String name;

    private Integer age;
    private Long createDate;

    @Column("bean_value")
    private String value;

    @Transient
    private LocalDate date;

    @Transient
    private LocalDateTime dateTime;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
