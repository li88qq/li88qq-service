package it;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.li88qq.db.dto.bean.BeanDto;
import com.li88qq.db.utils.BeanUtil;
import data.Bean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * BeanUtil测试
 *
 * @author li88qq
 * @version 1.0 2023/2/22 22:54
 */
public class BeanUtilTest {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    public void buildDto() {
        Class<Bean> beanClass = Bean.class;
        BeanDto beanDto = BeanUtil.buildDto(beanClass);
        LOG.info("BeanUtil buildDto Bean=>");
        LOG.info(JSON.toJSONString(beanDto));
    }

    @Test
    public void buildTable() {
        Class<Bean> beanClass = Bean.class;
        String table = BeanUtil.buildTable(beanClass);
        Assert.isTrue(table.equals("Bean_Table"), "buildTable错误");
        LOG.info("BeanUtil buildTable Bean=>");
        LOG.info(JSON.toJSONString(table));
    }

    @Test
    public void reset() {
        Class<Bean> beanClass = Bean.class;
        Bean bean = BeanUtil.reset(beanClass);
        LOG.info("BeanUtil reset Bean=>");
        LOG.info(JSON.toJSONString(bean, SerializerFeature.WriteMapNullValue));
    }
}
