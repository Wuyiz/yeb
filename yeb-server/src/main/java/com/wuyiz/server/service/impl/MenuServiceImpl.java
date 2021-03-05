package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.MenuMapper;
import com.wuyiz.server.pojo.Menu;
import com.wuyiz.server.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
