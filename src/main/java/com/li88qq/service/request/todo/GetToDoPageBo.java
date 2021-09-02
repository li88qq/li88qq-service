package com.li88qq.service.request.todo;

import com.li88qq.service.dto.PageBo;

/**
 * @author li88qq
 * @version 1.0 2021/9/2 22:47
 */
public class GetToDoPageBo extends PageBo {

    private Long labelId;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}
