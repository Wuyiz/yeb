package com.wuyiz.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wuyiz.server.pojo.Menu;
import com.wuyiz.server.pojo.MenuRole;
import com.wuyiz.server.pojo.Role;
import com.wuyiz.server.service.MenuRoleService;
import com.wuyiz.server.service.MenuService;
import com.wuyiz.server.service.RoleService;
import com.wuyiz.server.utils.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 * @author: suhai
 * @date: 2021-03-10 13:49
 * @description: 基础信息设置-菜单里的权限管理模块
 */
@RestController
@RequestMapping("system/basic/permiss")
public class PermissionController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Autowired
    MenuRoleService menuRoleService;

    @ApiOperation("获取所有的角色列表")
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation("获取所有的菜单列表")
    @GetMapping("menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation("根据角色id查询菜单id")
    @GetMapping("mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable("rid") Integer id) {
        //将menuRoleService查询出来的MenuRole对象里的菜单id提取出来返回
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", id)).stream()
                .map(MenuRole::getMid).collect(Collectors.toList());
    }

    @ApiOperation("添加角色")
    @PostMapping("role")
    public RespBean addRole(@RequestBody Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation("更新当前用户角色对菜单的权限")
    @PutMapping
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        return menuRoleService.updateMenuRole(rid, mids);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("role/{rid}")
    public RespBean deleteRole(@PathVariable(name = "rid") Integer id) {
        if (roleService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }


}
