package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.EmployeeMapper;
import com.wuyiz.server.pojo.Employee;
import com.wuyiz.server.service.EmployeeService;
import org.springframework.stereotype.Service;

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

}
