package com.wuyiz.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyiz.server.pojo.MenuRole;
import com.wuyiz.server.utils.RespBean;

/**
 * <p>
 * 菜单角色中间表 服务类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
public interface MenuRoleService extends IService<MenuRole> {
    /**
     * 更新当前用户角色对菜单的权限
     * @return
     * @param rid
     * @param mids
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
