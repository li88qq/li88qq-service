package web;

import com.li88qq.login.LoginApplication;
import com.li88qq.login.module.main.dto.login.LoginForm;
import com.li88qq.login.module.main.dto.register.RegisterForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 登录
 *
 * @author li88qq
 * @version 1.0 2022/3/22 21:34
 */
@SpringBootTest(classes = LoginApplication.class)
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    /**
     * 注册
     */
    @Test
    public void register() {
        String path = "/p/register";
        RegisterForm form = new RegisterForm();
        form.setUsername("li88qq");
        form.setPassword("123456");
        form.setMobile("13500000001");
        MvcTest.doPost(mvc, path, form);
    }

    /**
     * 登录
     */
    @Test
    public void login() {
        String path = "/p/login";
        LoginForm form = new LoginForm();
        form.setUsername("li88qq");
        form.setPassword("123456");
        form.setCode("A2Gb22");
        form.setCheckCode("6EI3RKF4");
        MvcTest.doPost(mvc, path, form);
    }

    /**
     * 登出
     */
    @Test
    public void logout() {
        String path = "/p/logout";
        MvcTest.doPost(mvc, path, null);
    }

}
