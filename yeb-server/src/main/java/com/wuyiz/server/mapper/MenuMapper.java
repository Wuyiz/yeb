package com.wuyiz.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyiz.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过用户id查询用户角色下属的菜单列表
     * @param id
     * @return
     */
    List<Menu> getMenuListByAdminId(Integer id);

    /**
     * 根据角色查询菜单列表
     * @return
     */
    List<Menu> getMenuWithRole();
}
