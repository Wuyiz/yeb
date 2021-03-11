package com.wuyiz.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyiz.server.pojo.Employee;

import java.time.LocalDate;

/**
 * <p>
 * 员工表 Mapper 接口
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 根据传入的条件查询出符合的员工信息
     * @param page
     * @param employee
     * @param beginDateScope
     * @return
     */
    IPage<Employee> getEmployeeByPage(Page<Employee> page, Employee employee, LocalDate[] beginDateScope);
}
