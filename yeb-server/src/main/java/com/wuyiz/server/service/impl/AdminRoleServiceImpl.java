package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.AdminRoleMapper;
import com.wuyiz.server.pojo.AdminRole;
import com.wuyiz.server.service.AdminRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员角色中间表 服务实现类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

}
