package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.MenuRoleMapper;
import com.wuyiz.server.pojo.MenuRole;
import com.wuyiz.server.service.MenuRoleService;
import com.wuyiz.server.utils.RespBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 菜单角色中间表 服务实现类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {
    @Resource
    MenuRoleMapper menuRoleMapper;

    /**
     * 1、删除此角色所有菜单；2、给该角色重新加上菜单；因是两步操作，开启事务
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        // 删除角色下所有菜单
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        // 判断传入的菜单id是否为空，如果为空则表示上一步操作已完成
        if (null == mids || 0 == mids.length) {
            return RespBean.success("更新成功");
        }
        // mids有值，添加传入的菜单id到当前rid下
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        // 返回的影响条数=mids的长度，说明插入成功
        if (result == mids.length) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
