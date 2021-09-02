package com.li88qq.service.serviceImpl;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.IdBo;
import com.li88qq.service.request.todo.GetToDoPageBo;
import com.li88qq.service.request.todo.SaveToDoBo;
import com.li88qq.service.request.todo.UpdateTodoBo;
import com.li88qq.service.response.GetTodoVo;
import com.li88qq.service.response.ToDoLabelVo;
import com.li88qq.service.response.ToDoVo;
import com.li88qq.service.service.IToDoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * todo服务
 *
 * @author li88qq
 * @version 1.0 2021/9/1 22:47
 */
@Service
public class ToDoService implements IToDoService {

    @Override
    public BaseResponse saveLabels(List<String> labels) {
        return null;
    }

    @Override
    public List<ToDoLabelVo> getLabels() {
        return null;
    }

    @Override
    public BaseResponse save(SaveToDoBo bo) {
        return null;
    }

    @Override
    public BaseResponse update(UpdateTodoBo bo) {
        return null;
    }

    @Override
    public BaseResponse finish(IdBo bo) {
        return null;
    }

    @Override
    public BaseResponse revert(IdBo bo) {
        return null;
    }

    @Override
    public BaseResponse delete(IdBo bo) {
        return null;
    }

    @Override
    public GetTodoVo getTodo(Long id) {
        return null;
    }

    @Override
    public List<ToDoVo> getList(Long labelId) {
        return null;
    }

    @Override
    public List<ToDoVo> getPage(GetToDoPageBo bo) {
        return null;
    }
}
