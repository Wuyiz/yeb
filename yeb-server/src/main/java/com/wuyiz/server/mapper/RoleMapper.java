package com.wuyiz.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyiz.server.pojo.Role;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
