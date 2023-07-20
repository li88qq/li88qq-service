package web;

import com.alibaba.fastjson2.JSON;
import com.li88qq.admin.AdminApplication;
import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageForm;
import com.li88qq.admin.module.menu.main.dto.menu.GetMenuPageVo;
import com.li88qq.admin.module.menu.main.dto.menu.SaveMenuForm;
import com.li88qq.admin.module.menu.main.service.MenuService;
import com.li88qq.db.dto.page.TPage;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 菜单测试
 *
 * @author li88qq
 * @version 1.0 2023/7/19 22:16
 */
@SpringBootTest(classes = AdminApplication.class)
public class MenuTest {

    private static final Logger LOG = LogManager.getLogger();

    @Resource
    private MenuService menuService;

    @Test
    public void save() {
        SaveMenuForm form = new SaveMenuForm();
        form.setName("测试菜单");
        form.setHref("/menu/list");
        form.setIcon("fa fa-cog");
        form.setOpenType(1);
        form.setParentId(0);
        menuService.save(form);
    }

    @Test
    public void saveChildren() {
        SaveMenuForm form = new SaveMenuForm();
        form.setName("测试子菜单");
        form.setHref("/menu/type");
        form.setIcon("fa fa-cog");
        form.setOpenType(1);
        form.setParentId(1);
        menuService.save(form);
    }

    @Test
    public void findPage() {
        GetMenuPageForm form = new GetMenuPageForm();
        form.setName("测试");
        form.setHref("menu");
        form.setIcon("fa");
        form.setParentId(1);
        TPage<GetMenuPageVo> page = menuService.page(form);
        LOG.info(JSON.toJSONString(page));
    }
}
