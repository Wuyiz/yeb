package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.DepartmentMapper;
import com.wuyiz.server.pojo.Department;
import com.wuyiz.server.service.DepartmentService;
import com.wuyiz.server.utils.RespBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    @Override
    public RespBean addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
        // 获取存储过程返回的插入条数结果
        if (1 == department.getResult()) {
            return RespBean.success("添加成功", department);
        }
        return RespBean.error("添加失败");
    }

    @Override
    public RespBean delDepartment(Integer id) {
        Department department = new Department();
        department.setId(id);
        // 传入要删除的部门id，调用存储过程
        departmentMapper.delDepartment(department);
        if (-2 == department.getResult()) {
            // 存储过程返回的结果为-2，说明部门是父节点
            return RespBean.error("该部门下还有其他子部门，无法删除");
        } else if (-1 == department.getResult()) {
            // 存储过程返回的结果为-1，说明部门下有员工
            return RespBean.error("该部门下还有员工，无法删除");
        } else if (1 == department.getResult()) {
            // 存储过程返回的结果为1，说明部门既不是父节点也没有员工
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }
}
