package com.wuyiz.server.controller;


import com.wuyiz.server.pojo.*;
import com.wuyiz.server.service.*;
import com.wuyiz.server.utils.RespBean;
import com.wuyiz.server.utils.RespPageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 员工表 前端控制器
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@RestController
@RequestMapping("employee/basic")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    private PoliticsStatusService politicsStatusService;
    @Autowired
    private JoblevelService joblevelService;
    @Autowired
    private NationService nationService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation("分页查询员工信息")
    @GetMapping
    public RespPageBean getEmployee(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "10") Integer size,
            Employee employee,
            LocalDate[] beginDateScope
    ) {
        return employeeService.getEmployeeByPage(currentPage, size, employee, beginDateScope);
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatus() {
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("joblevels")
    public List<Joblevel> getAllJoblevels() {
        return joblevelService.list();
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("nations")
    public List<Nation> getAllNations() {
        return nationService.list();
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("Positions")
    public List<Position> getAllPositions() {
        return positionService.list();
    }

    @ApiOperation(value = "获取所有部门")
    @GetMapping("deps")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "获取工号")
    @GetMapping("maxWorkID")
    public RespBean maxWorkID() {
        return employeeService.maxWorkID();
    }


    @ApiOperation(value = "添加员工")
    @PostMapping
    public RespBean addEmp(@RequestBody Employee employee) {
        return employeeService.addEmp(employee);
    }

    @ApiOperation(value = "更新员工")
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateById(employee)) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation(value = "删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id) {
        if (employeeService.removeById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

}
