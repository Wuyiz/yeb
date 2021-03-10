package com.wuyiz.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyiz.server.pojo.MenuRole;

/**
 * <p>
 * 菜单角色中间表 Mapper 接口
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
    /**
     * 更新用户角色对应的菜单
     * @param rid
     * @param mids
     * @return
     */
    Integer insertRecord(Integer rid, Integer[] mids);
}
