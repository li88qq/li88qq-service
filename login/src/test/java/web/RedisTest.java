package web;

import com.li88qq.login.LoginApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * redis
 *
 * @author li88qq
 * @version 1.0 2022/3/20 22:31
 */
@SpringBootTest(classes = LoginApplication.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void t1() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String test = ops.get("test");
        System.out.println(test);
        ops.set("test", "hello,world!");
        test = ops.get("test");
        System.out.println(test);
    }
}
