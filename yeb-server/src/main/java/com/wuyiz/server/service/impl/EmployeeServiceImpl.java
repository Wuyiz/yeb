package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.EmployeeMapper;
import com.wuyiz.server.pojo.Employee;
import com.wuyiz.server.service.EmployeeService;
import com.wuyiz.server.utils.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Resource
    EmployeeMapper employeeMapper;

    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        // 传入查询条件，并返回分页结果
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        //返回查询总数和结果到封装的分页类中
        return new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
    }
}
