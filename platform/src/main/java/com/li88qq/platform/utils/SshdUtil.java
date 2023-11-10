package com.li88qq.platform.utils;

import com.li88qq.platform.dto.BaseResult;
import com.li88qq.platform.dto.CmdResult;
import com.li88qq.platform.dto.SshdDto;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ChannelExec;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;

import java.io.ByteArrayOutputStream;
import java.util.EnumSet;

/**
 * sshd工具类
 *
 * @author li88qq
 * @version 1.0 2023/9/2 23:00
 */
public class SshdUtil {

    /**
     * 执行命令
     *
     * @param sshdDto 连接配置
     * @param cmd     执行命令
     * @return 响应结果
     */
    public static BaseResult<CmdResult> cmd(SshdDto sshdDto, String[] cmd) {
        try (SshClient client = SshClient.setUpDefaultClient()) {
            //启动
            client.start();
            //超时时间
            long timeOut = sshdDto.getTimeout() * 1000L;
            //连接
            ConnectFuture connect = client.connect(sshdDto.getUsername(), sshdDto.getHost(), sshdDto.getPort()).verify(timeOut);
            //session
            ClientSession session = connect.getSession();
            //密码
            session.addPasswordIdentity(sshdDto.getPassword());
            //用户认证
            session.auth().verify(timeOut);
            //执行,多个命令可以用 && 连接
            ChannelExec channel = session.createExecChannel(String.join(" && ", cmd));
            //输出
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //错误输出,java -version会通过err输出
            ByteArrayOutputStream err = new ByteArrayOutputStream();
            channel.setOut(out);
            channel.setErr(err);
            //打开通道
            channel.open().verify(timeOut);
            //等待
            channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), timeOut);
            //关闭
            channel.close();

            CmdResult cmdResult = new CmdResult();
            cmdResult.setOut(out.toString());
            cmdResult.setError(err.toString());

            return BaseResult.ok(cmdResult);
        } catch (Exception e) {
            return BaseResult.error(e.getMessage());
        }
    }

}
