package com.wuyiz.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyiz.server.pojo.Employee;
import com.wuyiz.server.utils.RespPageBean;

import java.time.LocalDate;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * 分页查询员工信息
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);
}
