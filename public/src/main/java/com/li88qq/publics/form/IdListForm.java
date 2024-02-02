package com.li88qq.publics.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * id列表
 *
 * @author li88qq
 * @version 1.0 2024/1/16 22:25
 */
public class IdListForm {

    @NotNull
    @Size(min = 1)
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
