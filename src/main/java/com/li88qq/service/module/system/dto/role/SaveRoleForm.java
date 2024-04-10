package com.li88qq.service.module.system.dto.role;

import jakarta.validation.constraints.NotBlank;

/**
 * @author li88qq
 * @version 1.0 2024/1/2 21:34
 */
public class SaveRoleForm {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
