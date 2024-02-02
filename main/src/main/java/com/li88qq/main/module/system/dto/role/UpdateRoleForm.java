package com.li88qq.main.module.system.dto.role;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author li88qq
 * @version 1.0 2024/1/2 21:35
 */
public class UpdateRoleForm {

    @NotNull
    @Min(value = 1)
    private Integer id;

    @NotBlank
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
