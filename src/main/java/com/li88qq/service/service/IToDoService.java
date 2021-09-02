package com.li88qq.service.service;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.IdBo;
import com.li88qq.service.request.todo.GetToDoPageBo;
import com.li88qq.service.request.todo.SaveToDoBo;
import com.li88qq.service.request.todo.UpdateTodoBo;
import com.li88qq.service.response.GetTodoVo;
import com.li88qq.service.response.ToDoLabelVo;
import com.li88qq.service.response.ToDoVo;

import java.util.List;

/**
 * todo服务接口
 *
 * @author li88qq
 * @version 1.0 2021/9/1 22:19
 */
public interface IToDoService {

    /**
     * 新增todo标签
     */
    BaseResponse saveLabels(List<String> labels);

    /**
     * 查询todo标签列表
     */
    List<ToDoLabelVo> getLabels();

    /**
     * 保存todo
     */
    BaseResponse save(SaveToDoBo bo);

    /**
     * 修改todo
     */
    BaseResponse update(UpdateTodoBo bo);

    /**
     * 完成todo
     */
    BaseResponse finish(IdBo bo);

    /**
     * 重新打开todo
     */
    BaseResponse revert(IdBo bo);

    /**
     * 删除todo
     */
    BaseResponse delete(IdBo bo);

    /**
     * 根据id查询todo
     */
    GetTodoVo getTodo(Long id);

    /**
     * 查询todo列表
     */
    List<ToDoVo> getList(Long labelId);

    /**
     * 分页查询todo列表
     */
    List<ToDoVo> getPage(GetToDoPageBo bo);
}
