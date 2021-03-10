package com.wuyiz.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyiz.server.pojo.Department;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 递归查询所有部门信息
     * @param parentId
     * @return
     */
    List<Department> getAllDepartments(Integer parentId);

    /**
     * 调用存储过程添加部门
     * @param department
     */
    void addDepartment(Department department);

    /**
     * 调用存储过程删除部门
     * @param department
     */
    void delDepartment(Department department);
}
