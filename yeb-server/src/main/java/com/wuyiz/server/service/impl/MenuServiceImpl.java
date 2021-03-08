package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.MenuMapper;
import com.wuyiz.server.pojo.Admin;
import com.wuyiz.server.pojo.Menu;
import com.wuyiz.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    MenuMapper menuMapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private static final String MENU_PRE_IN_REDIS = "menu_";

    @Override
    public List<Menu> getMenuListByAdminId() {
        //通过全局对象获取到用户id
        Integer id = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        //创建redis的string类型的操作对象
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //首先从Redis中查询是否存在菜单缓存
        List<Menu> menus = (List<Menu>) valueOperations.get(MENU_PRE_IN_REDIS + id);
        //如果redis中没有菜单缓存，则查询数据库并将菜单保存在redis中
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.getMenuListByAdminId(id);
            valueOperations.set(MENU_PRE_IN_REDIS + id, menus);
        }
        return menus;
    }
}
