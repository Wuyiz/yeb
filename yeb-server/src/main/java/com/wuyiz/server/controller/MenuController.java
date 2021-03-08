package com.wuyiz.server.controller;


import com.wuyiz.server.pojo.Menu;
import com.wuyiz.server.service.MenuService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@RestController
@RequestMapping("system/config")
public class MenuController {
    @Autowired
    MenuService menuService;

    @ApiModelProperty(value = "通过登录用户得id查询菜单列表")
    @GetMapping("menu")
    public List<Menu> getMenuListByAdminId() {
        return menuService.getMenuListByAdminId();
    }
}
