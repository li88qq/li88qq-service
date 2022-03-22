package web;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author li88qq
 * @version 1.0 2022/3/22 22:03
 */
public class MvcTest {

    private static final Logger LOG = LogManager.getLogger();

    public static void doGet(MockMvc mvc, String path, Object... params) {
        try {
            String data = JSON.toJSONString(params);
            mvc.perform(get(path, params)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data))
                    .andExpect(status().isOk())
                    .andDo((result) -> {
                        handleResult(path, data, result);
                    });
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public static void doPost(MockMvc mvc, String path, Object form) {
        try {
            String data = JSON.toJSONString(form);
            mvc.perform(post(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(data))
                    .andExpect(status().isOk())
                    .andDo((result) -> {
                        handleResult(path, data, result);
                    });
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    private static void handleResult(String path, String data, MvcResult result) throws Exception {
        String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        LOG.info("========================================================================");
        LOG.info("POST {}", path);
        LOG.info("data: {}", data);
        LOG.info(content);
        LOG.info("=================================end====================================");
    }

}
