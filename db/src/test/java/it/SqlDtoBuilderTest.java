package it;

import com.alibaba.fastjson.JSON;
import com.li88qq.db.dto.sql.SqlDto;
import com.li88qq.db.utils.SqlDtoBuilder;
import data.Bean;
import data.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

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
    }

    /**
     * buildInsert
     */
    @Test
    public void buildInsertList() {
        SqlDto sqlDto = SqlDtoBuilder.buildInsert(Bean.class, true);
        LOG.info(JSON.toJSONString(sqlDto));
    }

    /**
     * buildInsert
     */
    @Test
    public void buildInsert_user() {
        SqlDto sqlDto = SqlDtoBuilder.buildInsert(User.class, false);
        LOG.info(JSON.toJSONString(sqlDto));
    }
}
