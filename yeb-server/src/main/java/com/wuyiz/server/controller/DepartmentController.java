package com.wuyiz.server.controller;


import com.wuyiz.server.pojo.Department;
import com.wuyiz.server.service.DepartmentService;
import com.wuyiz.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@RestController
@RequestMapping("system/basic/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @ApiOperation("查询所有部门列表")
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @ApiOperation("添加部门")
    @PostMapping
    public RespBean addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    public RespBean delDepartment(@PathVariable Integer id) {
        return departmentService.delDepartment(id);
    }

}
