package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.DepartmentMapper;
import com.wuyiz.server.pojo.Department;
import com.wuyiz.server.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
