package com.li88qq.service.controller;

import com.li88qq.service.constant.AcLogConst;
import com.li88qq.service.constant.annitions.AcLog;
import com.li88qq.service.constant.enumeration.ActionType;
import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.IdBo;
import com.li88qq.service.request.todo.GetToDoPageBo;
import com.li88qq.service.request.todo.SaveToDoBo;
import com.li88qq.service.request.todo.UpdateTodoBo;
import com.li88qq.service.response.GetTodoVo;
import com.li88qq.service.response.ToDoLabelVo;
import com.li88qq.service.response.ToDoVo;
import com.li88qq.service.service.IToDoService;
import org.fastquery.page.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * todo控制
 *
 * @author li88qq
 * @version 1.0 2021/8/31 23:15
 */
@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Resource
    private IToDoService toDoService;

    /**
     * 新增todo标签
     *
     * @param labels
     * @return
     */
    @PostMapping("/saveLabels")
    @AcLog(acType = ActionType.SAVE, title = "新增todo标签", detail = "labels", prefix = AcLogConst.TODO)
    public BaseResponse saveLabels(@RequestBody List<String> labels) {
        return toDoService.saveLabels(labels);
    }

    /**
     * 查询todo标签列表
     *
     * @return
     */
    @GetMapping("/labels")
    public List<ToDoLabelVo> getLabels() {
        return toDoService.getLabels();
    }

    /**
     * 保存todo
     *
     * @param bo
     * @return
     */
    @PostMapping("/save")
    @AcLog(acType = ActionType.SAVE, title = "新增todo", detail = "bo", prefix = AcLogConst.TODO)
    public BaseResponse save(@RequestBody SaveToDoBo bo) {
        return toDoService.save(bo);
    }

    /**
     * 修改todo
     *
     * @param bo
     * @return
     */
    @PostMapping("/update")
    @AcLog(acType = ActionType.UPDATE, title = "修改todo", detail = "bo", prefix = AcLogConst.TODO)
    public BaseResponse update(@RequestBody UpdateTodoBo bo) {
        return toDoService.update(bo);
    }

    /**
     * 完成todo
     *
     * @param bo
     * @return
     */
    @PostMapping("/finish")
    @AcLog(acType = ActionType.UPDATE, title = "完成todo", detail = "bo", prefix = AcLogConst.TODO)
    public BaseResponse finish(@RequestBody IdBo bo) {
        return toDoService.finish(bo);
    }

    /**
     * 还原todo
     *
     * @param bo
     * @return
     */
    @PostMapping("/revert")
    @AcLog(acType = ActionType.UPDATE, title = "重新打开todo", detail = "bo", prefix = AcLogConst.TODO)
    public BaseResponse revert(@RequestBody IdBo bo) {
        return toDoService.revert(bo);
    }

    /**
     * 删除todo
     *
     * @param bo
     * @return
     */
    @PostMapping("/delete")
    @AcLog(acType = ActionType.DELETE, title = "删除todo", detail = "bo", prefix = AcLogConst.TODO)
    public BaseResponse delete(@RequestBody IdBo bo) {
        return toDoService.delete(bo);
    }

    /**
     * 根据id查询todo
     *
     * @param id
     * @return
     */
    @GetMapping("/getTodo")
    public GetTodoVo getTodo(@RequestParam Long id) {
        return toDoService.getTodo(id);
    }

    /**
     * 查询todo列表
     *
     * @param labelId
     * @return
     */
    @GetMapping("/list")
    public List<ToDoVo> getList(@RequestParam(required = false) Long labelId) {
        return toDoService.getList(labelId);
    }

    /**
     * 分页查询todo列表
     *
     * @param bo
     * @return
     */
    @GetMapping("/page")
    public Page<ToDoVo> getPage(GetToDoPageBo bo) {
        return toDoService.getPage(bo);
    }
}
