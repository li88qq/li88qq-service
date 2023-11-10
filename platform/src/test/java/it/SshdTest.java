package it;

import com.alibaba.fastjson2.JSON;
import com.li88qq.platform.dto.BaseResult;
import com.li88qq.platform.dto.CmdResult;
import com.li88qq.platform.dto.SshdDto;
import com.li88qq.platform.utils.SshdUtil;
import org.junit.jupiter.api.Test;

/**
 * sshd测试
 *
 * @author li88qq
 * @version 1.0 2023/11/10 22:04
 */
public class SshdTest {

    @Test
    public void t1() {
        SshdDto dto = new SshdDto();
        dto.setHost("192.168.56.105");
        dto.setPort(22);
        dto.setUsername("root");
        dto.setPassword("123");
        dto.setTimeout(30);
        String[] cmd = new String[]{"ip addr", "who", "java -version"};
        BaseResult<CmdResult> result = SshdUtil.cmd(dto, cmd);
        System.out.println(JSON.toJSONString(result));

        if (result.isSuccess()) {
            CmdResult data = result.getData();
            log(data.getOut());
            log(data.getError());
        }
    }

    /**
     * 输出内容
     * @param data 数据
     */
    private void log(String data) {
        System.out.println();
        if (data == null) {
            return;
        }
        String[] lines = data.split("\n");
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
