package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.EmployeeMapper;
import com.wuyiz.server.pojo.Employee;
import com.wuyiz.server.service.EmployeeService;
import com.wuyiz.server.utils.RespBean;
import com.wuyiz.server.utils.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

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

    @Override
    public RespBean maxWorkID() {
        // 查询当前员工表中工号最大值
        List<Map<String, Object>> maps = baseMapper.selectMaps(new QueryWrapper<Employee>().select("max(work_id)"));
        // 返回下一个工号，即最大工号+1
        return RespBean.success(null, String.format("%08d", Integer.parseInt(maps.get(0).get("max(work_id)").toString()) + 1));

    }

    @Override
    public RespBean addEmp(Employee employee) {
        // 处理合同期限，保留两位小数
        LocalDateTime beginContract = employee.getBeginContract();  // 合同起始时间
        LocalDateTime endContract = employee.getEndContract();      // 合同终止时间
        // 计算两个日期的时间差
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        // 保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));
        if (1 == employeeMapper.insert(employee)) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }
}
