package it;

import com.alibaba.fastjson.JSON;
import com.li88qq.db.dto.sql.SqlDto;
import com.li88qq.db.utils.SqlDtoBuilder;
import data.Bean;
import data.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * SqlDtoBuilderTest
 *
 * @author li88qq
 * @version 1.0 2023/2/24 22:43
 */
public class SqlDtoBuilderTest {

    private static final Logger LOG = LogManager.getLogger();

    /**
     * buildInsert
     */
    @Test
    public void buildInsert() {
        SqlDto sqlDto = SqlDtoBuilder.buildInsert(Bean.class, false);

        LOG.info(JSON.toJSONString(sqlDto));

        String ignore = "";
        String table = "Bean_Table";
        String keys = "id,bean_name,age,createDate,bean_value";
        String values = "#{dto.id},#{dto.name},#{dto.age},#{dto.createDate},#{dto.value}";

        Assert.isTrue(sqlDto.getIgnore().equals(ignore), "ignore错误");
        Assert.isTrue(sqlDto.getTable().equals(table), "table错误");
        Assert.isTrue(sqlDto.getKeys().equals(keys), "keys错误");
        Assert.isTrue(sqlDto.getValues().equals(values), "values错误");
    }

    /**
     * buildInsert
     */
    @Test
    public void buildInsertList() {
        SqlDto sqlDto = SqlDtoBuilder.buildInsert(Bean.class, true);
        LOG.info(JSON.toJSONString(sqlDto));

        String ignore = "ignore";
        String table = "Bean_Table";
        String keys = "id,bean_name,age,createDate,bean_value";
        String values = "#{dto.id},#{dto.name},#{dto.age},#{dto.createDate},#{dto.value}";

        Assert.isTrue(sqlDto.getIgnore().equals(ignore), "ignore错误");
        Assert.isTrue(sqlDto.getTable().equals(table), "table错误");
        Assert.isTrue(sqlDto.getKeys().equals(keys), "keys错误");
        Assert.isTrue(sqlDto.getValues().equals(values), "values错误");
    }

    /**
     * buildInsert
     */
    @Test
    public void buildInsert_user() {
        SqlDto sqlDto = SqlDtoBuilder.buildInsert(User.class, false);
        LOG.info(JSON.toJSONString(sqlDto));

        String ignore = "";
        String table = "User";
        String keys = "id,name";
        String values = "#{dto.id},#{dto.name}";

        Assert.isTrue(sqlDto.getIgnore().equals(ignore), "ignore错误");
        Assert.isTrue(sqlDto.getTable().equals(table), "table错误");
        Assert.isTrue(sqlDto.getKeys().equals(keys), "keys错误");
        Assert.isTrue(sqlDto.getValues().equals(values), "values错误");
    }

    /**
     * buildUpdate
     */
    @Test
    public void buildUpdate() {
        SqlDto sqlDto = SqlDtoBuilder.buildUpdate(Bean.class);
        LOG.info(JSON.toJSONString(sqlDto));

        String table = "Bean_Table";
        String set = "bean_name = #{dto.name},age = #{dto.age},createDate = #{dto.createDate},bean_value = #{dto.value}";
        String where = "id = #{dto.id}";

        Assert.isTrue(sqlDto.getTable().equals(table), "table错误");
        Assert.isTrue(sqlDto.getSet().equals(set), "set错误");
        Assert.isTrue(sqlDto.getWhere().equals(where), "where错误");
    }

    /**
     * buildUpdate
     */
    @Test
    public void buildUpdate_user() {
        SqlDto sqlDto = SqlDtoBuilder.buildUpdate(User.class);
        LOG.info(JSON.toJSONString(sqlDto));

        String table = "User";
        String set = "name = #{dto.name}";
        String where = "id = #{dto.id}";

        Assert.isTrue(sqlDto.getTable().equals(table), "table错误");
        Assert.isTrue(sqlDto.getSet().equals(set), "set错误");
        Assert.isTrue(sqlDto.getWhere().equals(where), "where错误");
    }

    /**
     * buildDelete
     */
    @Test
    public void buildDelete() {
        SqlDto sqlDto = SqlDtoBuilder.buildDelete(Bean.class);
        LOG.info(JSON.toJSONString(sqlDto));

        String table = "Bean_Table";
        String where = "id = #{dto.id}";

        Assert.isTrue(sqlDto.getTable().equals(table), "table错误");
        Assert.isTrue(sqlDto.getWhere().equals(where), "where错误");
    }

}
