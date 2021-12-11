package it;

import com.li88qq.db.dto.BeanDto;
import com.li88qq.db.utils.BeanUtil;
import com.li88qq.db.utils.SqlFactory;
import it.entity.Record;
import it.entity.RecordLog;
import org.junit.jupiter.api.Test;

/**
 * SqlFactory测试
 *
 * @author li88qq
 * @version 1.0 2021/12/11 16:02
 */
public class SqlFactoryTest {

    /**
     * insert测试1(单条Record记录)
     */
    @Test
    public void buildInsertSql_1() {
        BeanDto beanDto = BeanUtil.buildBeanDto(Record.class);
        String sql = SqlFactory.buildInsertSql(false, beanDto, 1);
        assert sql.equals("insert into Record (id,name,createDate) values (?,?,?);");
    }

    /**
     * insert测试2(多条Record记录,不忽略重复)
     */
    @Test
    public void buildInsertSql_2() {
        BeanDto beanDto = BeanUtil.buildBeanDto(Record.class);
        String sql = SqlFactory.buildInsertSql(false, beanDto, 2);
        assert sql.equals("insert into Record (id,name,createDate) values (?,?,?),(?,?,?);");
    }

    /**
     * insert测试3(多条Record记录,忽略重复)
     */
    @Test
    public void buildInsertSql_3() {
        BeanDto beanDto = BeanUtil.buildBeanDto(Record.class);
        String sql = SqlFactory.buildInsertSql(true, beanDto, 3);
        assert sql.equals("insert ignore into Record (id,name,createDate) values (?,?,?),(?,?,?),(?,?,?);");
    }

    /**
     * update测试1(一个主键id)
     */
    @Test
    public void buildUpdateSql_1() {
        BeanDto beanDto = BeanUtil.buildBeanDto(Record.class);
        String sql = SqlFactory.buildUpdateSql(beanDto);
        assert sql.equals("update Record set name = ?,createDate = ? where id = ?;");
    }

    /**
     * update测试1(多个主键id)
     */
    @Test
    public void buildUpdateSql_2() {
        BeanDto beanDto = BeanUtil.buildBeanDto(RecordLog.class);
        String sql = SqlFactory.buildUpdateSql(beanDto);
        assert sql.equals("update RecordLog set name = ? where id = ? and recordId = ?;");
    }

    /**
     * delete测试1(一个主键id)
     */
    @Test
    public void buildDeleteSql_1() {
        BeanDto beanDto = BeanUtil.buildBeanDto(Record.class);
        String sql = SqlFactory.buildDeleteSql(beanDto);
        assert sql.equals("delete from Record where id = ?;");
    }

    /**
     * delete测试1(多个主键id)
     */
    @Test
    public void buildDeleteSql_2() {
        BeanDto beanDto = BeanUtil.buildBeanDto(RecordLog.class);
        String sql = SqlFactory.buildDeleteSql(beanDto);
        assert sql.equals("delete from RecordLog where id = ? and recordId = ?;");
    }

    /**
     * saveOrUpdate(一条记录)
     */
    @Test
    public void buildSaveOrUpdateSql_1() {
        BeanDto beanDto = BeanUtil.buildBeanDto(Record.class);
        String sql = SqlFactory.buildSaveOrUpdateSql(beanDto, 1);
        assert sql.equals("insert into Record (id,name,createDate) values (?,?,?) as alias " +
                "on duplicate key update id = alias.id,name = alias.name,createDate = alias.createDate;");
    }

    /**
     * saveOrUpdate(多条记录)
     */
    @Test
    public void buildSaveOrUpdateSql_2() {
        BeanDto beanDto = BeanUtil.buildBeanDto(Record.class);
        String sql = SqlFactory.buildSaveOrUpdateSql(beanDto, 2);
        assert sql.equals("insert into Record (id,name,createDate) values (?,?,?),(?,?,?) as alias " +
                "on duplicate key update id = alias.id,name = alias.name,createDate = alias.createDate;");
    }

    /**
     * 批量更新(一个主键id,一条记录)
     */
    @Test
    public void buildUpdateBatchSql_1() {
        BeanDto beanDto = BeanUtil.buildBeanDto(Record.class);
        String sql = SqlFactory.buildUpdateBatchSql(beanDto, 1);
        assert sql.equals("update Record set name = case id when ? then ? end,createDate = case id when ? then ? end " +
                "where id in (?);");
    }

    /**
     * 批量更新(一个主键id,多条记录)
     */
    @Test
    public void buildUpdateBatchSql_2() {
        BeanDto beanDto = BeanUtil.buildBeanDto(Record.class);
        String sql = SqlFactory.buildUpdateBatchSql(beanDto, 2);
        assert sql.equals("update Record set name = case id when ? then ? when ? then ? end," +
                "createDate = case id when ? then ? when ? then ? end " +
                "where id in (?,?);");
    }

}
