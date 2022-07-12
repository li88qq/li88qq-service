package com.li88qq.bean.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * ids请求参数
 *
 * @author li88qq
 * @version 1.0 2022/4/17 22:29
 */
public class IdsForm {

    @NotNull
    @Pattern(regexp = "\\d+]")
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
