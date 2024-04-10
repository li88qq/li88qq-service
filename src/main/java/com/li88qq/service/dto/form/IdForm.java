package com.li88qq.service.dto.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * id
 *
 * @author li88qq
 * @version 1.0 2024/1/16 22:23
 */
public class IdForm {

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
