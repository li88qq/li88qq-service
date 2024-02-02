package com.li88qq.main.module.system.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * @author li88qq
 * @version 1.0 2024/1/1 22:14
 */
public class UserIdForm {

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
