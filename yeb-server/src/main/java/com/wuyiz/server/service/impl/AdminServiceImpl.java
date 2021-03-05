package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.AdminMapper;
import com.wuyiz.server.pojo.Admin;
import com.wuyiz.server.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
