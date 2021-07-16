package web;

import com.li88qq.service.controller.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

@WebMvcTest(TestController.class)
public class MockTest {

    @Resource
    private MockMvc mvc;

    @Test
    public void test() throws Exception {
        System.out.println(mvc);
    }

}
