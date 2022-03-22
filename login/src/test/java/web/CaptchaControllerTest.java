package web;

import com.li88qq.login.LoginApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 验证码
 *
 * @author li88qq
 * @version 1.0 2022/3/22 22:40
 */
@SpringBootTest(classes = LoginApplication.class)
@AutoConfigureMockMvc
public class CaptchaControllerTest {
    @Autowired
    private MockMvc mvc;

    /**
     * 获取验证码
     */
    @Test
    public void getCaptcha() {
        String path = "/p/getCaptcha";
        MvcTest.doGet(mvc, path);
    }
}
