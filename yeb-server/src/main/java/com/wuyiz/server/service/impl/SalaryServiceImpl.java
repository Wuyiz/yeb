package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.SalaryMapper;
import com.wuyiz.server.pojo.Salary;
import com.wuyiz.server.service.SalaryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工资表 服务实现类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements SalaryService {

}
