package com.wuyiz.server.controller;


import com.wuyiz.server.pojo.Joblevel;
import com.wuyiz.server.service.JoblevelService;
import com.wuyiz.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 职称等级表 前端控制器
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@RestController
@RequestMapping("system/basic/joblevel")
public class JoblevelController {
    @Autowired
    JoblevelService joblevelService;

    @ApiOperation(value = "查询所有职称等级列表")
    @GetMapping
    public List<Joblevel> getAllPosition() {
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职称等级信息")
    @PostMapping
    public RespBean addPosition(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更新职称等级信息")
    @PutMapping
    public RespBean updatePosition(@RequestBody Joblevel joblevel) {
        if (joblevelService.updateById(joblevel)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除职称等级信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id) {
        if (joblevelService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除职称等级信息")
    @DeleteMapping
    public RespBean deletePositionList(Integer[] ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
