package com.wuyiz.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyiz.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
public interface MenuService extends IService<Menu> {

    /**
     *
     * @return
     */
    List<Menu> getMenuListByAdminId();
}
