package com.li88qq.service.dto.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * id
 *
 * @author li88qq
 * @version 1.0 2024/1/16 22:24
 */
public class IdLongForm {

    @NotNull
    @Min(value = 1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
