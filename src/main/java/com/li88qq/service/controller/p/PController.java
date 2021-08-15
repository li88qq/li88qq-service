package com.li88qq.service.controller.p;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.SessionCode;
import com.li88qq.service.request.article.ReadBo;
import com.li88qq.service.response.GetArticleBySnVo;
import com.li88qq.service.service.IArticleService;
import com.li88qq.service.utils.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 验证码
 */
@Controller
@RequestMapping("/p")
public class PController {

    @Resource
    private DefaultKaptcha defaultKaptcha;
    @Resource
    private IArticleService articleService;

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String code = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(code);
        response.setContentType("image/jpeg");

        //放入session,3分钟有效
        HttpSession session = SessionUtil.getSession(true);
        SessionCode sessionCode = new SessionCode();
        sessionCode.setCode(code);
        sessionCode.setDateTime(LocalDateTime.now().plus(3, ChronoUnit.MINUTES));
        session.setAttribute("captcha", sessionCode);

        try (ServletOutputStream stream = response.getOutputStream()) {
            ImageIO.write(image, "jpg", stream);
            stream.flush();
        } catch (IOException e) {
        }
    }

    /**
     * 获取ip
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/ip")
    public String getIp() {
        return SessionUtil.getIp();
    }

    /**
     * 根据sn查询文章
     *
     * @param sn
     * @return
     */
    @ResponseBody
    @GetMapping("/article")
    public GetArticleBySnVo getArticle(@RequestParam(name = "id") @NotBlank(message = "参数错误") String sn) {
        return articleService.getArticleBySn(sn);
    }

    /**
     * 阅读文章
     *
     * @param bo
     * @return
     */
    @ResponseBody
    @PostMapping("/readArticle")
    public BaseResponse read(@RequestBody ReadBo bo) {
        return articleService.read(bo);
    }
}
