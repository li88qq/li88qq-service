package com.li88qq.main.module.system.dto.menu;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * @author li88qq
 * @version 1.0 2024/1/2 22:28
 */
public class MenuIdForm {

    @NotNull
    @Min(value = 1)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
