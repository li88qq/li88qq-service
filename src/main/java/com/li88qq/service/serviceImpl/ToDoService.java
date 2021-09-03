package com.li88qq.service.serviceImpl;

import com.li88qq.service.dto.BaseResponse;
import com.li88qq.service.dto.IdBo;
import com.li88qq.service.entity.ToDo;
import com.li88qq.service.entity.ToDoLabel;
import com.li88qq.service.repo.ToDoLabelRepo;
import com.li88qq.service.repo.ToDoRepo;
import com.li88qq.service.request.todo.GetToDoPageBo;
import com.li88qq.service.request.todo.SaveToDoBo;
import com.li88qq.service.request.todo.UpdateTodoBo;
import com.li88qq.service.response.GetTodoVo;
import com.li88qq.service.response.ToDoLabelVo;
import com.li88qq.service.response.ToDoVo;
import com.li88qq.service.service.IToDoService;
import com.li88qq.service.utils.DateUtil;
import com.li88qq.service.utils.ResponseUtil;
import com.li88qq.service.utils.SessionUtil;
import com.li88qq.service.utils.StringUtil;
import org.fastquery.page.Page;
import org.fastquery.page.Pageable;
import org.fastquery.page.PageableImpl;
import org.fastquery.service.FQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * todo服务
 *
 * @author li88qq
 * @version 1.0 2021/9/1 22:47
 */
@Service
public class ToDoService implements IToDoService {

    @Resource
    private ToDoLabelRepo toDoLabelRepo;
    @Resource
    private ToDoRepo toDoRepo;

    /**
     * 保存todo标签
     *
     * @param labels
     * @return
     */
    @Override
    public BaseResponse saveLabels(List<String> labels) {
        if (labels == null || labels.isEmpty()) {
            return ResponseUtil.error("参数错误");
        }
        Long uid = SessionUtil.getUid();
        List<ToDoLabel> list = new ArrayList<>();
        labels.forEach(label -> {
            label = StringUtil.trim(label);
            if (!label.equals("")) {
                ToDoLabel toDoLabel = new ToDoLabel(uid, label);
                list.add(toDoLabel);
            }
        });

        if (list.isEmpty()) {
            return ResponseUtil.error("数据为空");
        }
        toDoLabelRepo.save(false, list);
        return ResponseUtil.ok();
    }

    /**
     * 查询todo标签列表
     *
     * @return
     */
    @Override
    public List<ToDoLabelVo> getLabels() {
        Long uid = SessionUtil.getUid();
        List<ToDoLabelVo> list = toDoLabelRepo.findList(uid);
        return list;
    }

    /**
     * 保存todo
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse save(SaveToDoBo bo) {
        Long uid = SessionUtil.getUid();
        String content = bo.getContent();
        content = StringUtil.trim(content);
        if (content.equals("")) {
            return ResponseUtil.error("参数错误");
        }
        LocalDate beginDate = bo.getBeginDate();
        LocalDate endDate = bo.getEndDate();
        Long beginDateValue = 0L;
        Long endDateValue = 0L;
        if (beginDate != null) {
            beginDateValue = DateUtil.getTimestamp(beginDate.atTime(LocalTime.MIN));
        }
        if (endDate != null) {
            endDateValue = DateUtil.getTimestamp(endDate.atTime(LocalTime.MAX));
        }

        ToDo toDo = new ToDo(uid, bo.getLabelId(), bo.getContent());
        toDo.setBeginDate(beginDateValue);
        toDo.setEndDate(endDateValue);
        toDo.setSort(bo.getSort());

        toDoRepo.save(toDo);
        return ResponseUtil.ok();
    }

    /**
     * 修改todo
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse update(UpdateTodoBo bo) {
        Long id = bo.getId();
        Long uid = SessionUtil.getUid();

        ToDo toDo = toDoRepo.findById(uid, id);
        if (toDo == null) {
            return ResponseUtil.error("数据不存在");
        }

        String content = bo.getContent();
        content = StringUtil.trim(content);
        if (content.equals("")) {
            return ResponseUtil.error("参数错误");
        }
        LocalDate beginDate = bo.getBeginDate();
        LocalDate endDate = bo.getEndDate();
        Long beginDateValue = 0L;
        Long endDateValue = 0L;
        if (beginDate != null) {
            beginDateValue = DateUtil.getTimestamp(beginDate.atTime(LocalTime.MIN));
        }
        if (endDate != null) {
            endDateValue = DateUtil.getTimestamp(endDate.atTime(LocalTime.MAX));
        }

        toDo.setContent(content);
        toDo.setLabelId(bo.getLabelId());
        toDo.setBeginDate(beginDateValue);
        toDo.setEndDate(endDateValue);
        toDo.setSort(bo.getSort());
        toDo.setUpdateDate(DateUtil.getTimestamp());

        toDoRepo.update(toDo);
        return ResponseUtil.ok();
    }

    /**
     * 完成todo
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse finish(IdBo bo) {
        Long id = bo.getId();
        Long uid = SessionUtil.getUid();

        ToDo toDo = toDoRepo.findById(uid, id);
        if (toDo == null) {
            return ResponseUtil.error("数据不存在");
        }
        Integer state = toDo.getState();
        if (state == 1) {
            return ResponseUtil.error("状态错误");
        }
        ToDo updateTodo = FQuery.reset(ToDo.class);
        updateTodo.setId(id);
        updateTodo.setState(1);
        updateTodo.setFinishDate(DateUtil.getTimestamp());

        toDoRepo.executeUpdate(updateTodo);
        return ResponseUtil.ok();
    }

    /**
     * 重新打开todo
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse revert(IdBo bo) {
        Long id = bo.getId();
        Long uid = SessionUtil.getUid();

        ToDo toDo = toDoRepo.findById(uid, id);
        if (toDo == null) {
            return ResponseUtil.error("数据不存在");
        }
        Integer state = toDo.getState();
        if (state != 1) {
            return ResponseUtil.error("状态错误");
        }
        ToDo updateTodo = FQuery.reset(ToDo.class);
        updateTodo.setId(id);
        updateTodo.setState(0);
        updateTodo.setFinishDate(0L);

        toDoRepo.executeUpdate(updateTodo);
        return ResponseUtil.ok();
    }

    /**
     * 删除todo
     *
     * @param bo
     * @return
     */
    @Override
    public BaseResponse delete(IdBo bo) {
        Long id = bo.getId();
        Long uid = SessionUtil.getUid();

        ToDo toDo = toDoRepo.findById(uid, id);
        if (toDo == null) {
            return ResponseUtil.error("数据不存在");
        }
        toDoRepo.deleteById(id);
        return ResponseUtil.ok();
    }

    /**
     * 根据id获取todo
     *
     * @param id
     * @return
     */
    @Override
    public GetTodoVo getTodo(Long id) {
        Long uid = SessionUtil.getUid();

        ToDo toDo = toDoRepo.findById(uid, id);
        if (toDo == null) {
            throw new RuntimeException("数据不存在");
        }
        GetTodoVo vo = new GetTodoVo();
        vo.setId(id);
        vo.setContent(toDo.getContent());
        vo.setBeginDate(toDo.getBeginDate());
        vo.setEndDate(toDo.getEndDate());
        vo.setLabelId(toDo.getLabelId());
        vo.setSort(toDo.getSort());
        return vo;
    }

    /**
     * 查询todo列表
     *
     * @param labelId
     * @return
     */
    @Override
    public List<ToDoVo> getList(Long labelId) {
        Long uid = SessionUtil.getUid();
        List<ToDoVo> list = toDoRepo.findList(uid, labelId);
        return list;
    }

    /**
     * 分页查询todo
     *
     * @param bo
     * @return
     */
    @Override
    public Page<ToDoVo> getPage(GetToDoPageBo bo) {
        Long uid = SessionUtil.getUid();
        Pageable pageable = new PageableImpl(bo.getPage(), bo.getSize());
        Page<ToDoVo> pageData = toDoRepo.findPage(uid, bo.getLabelId(), pageable);
        return pageData;
    }
}
