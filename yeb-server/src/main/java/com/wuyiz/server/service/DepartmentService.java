package com.wuyiz.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyiz.server.pojo.Department;
import com.wuyiz.server.utils.RespBean;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 查询所有部门信息
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 调用存储过程添加部门
     * @param department
     * @return
     */
    RespBean addDepartment(Department department);

    /**
     * 调用存储过程删除部门
     * @param id
     * @return
     */
    RespBean delDepartment(Integer id);
}
