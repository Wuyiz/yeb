package com.wuyiz.server.controller;


import com.wuyiz.server.pojo.Position;
import com.wuyiz.server.service.PositionService;
import com.wuyiz.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 职位 前端控制器
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@RestController
@RequestMapping("system/basic/pos")
public class PositionController {
    @Autowired
    PositionService positionService;

    @ApiOperation(value = "查询所有职位列表")
    @GetMapping
    public List<Position> getAllPosition() {
        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping
    public RespBean addPosition(@RequestBody Position position) {
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping
    public RespBean deletePositionList(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
