package com.wuyiz.server.controller;


import com.wuyiz.server.pojo.Employee;
import com.wuyiz.server.service.EmployeeService;
import com.wuyiz.server.utils.RespPageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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

}
