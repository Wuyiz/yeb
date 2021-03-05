package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.RoleMapper;
import com.wuyiz.server.pojo.Role;
import com.wuyiz.server.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
