package com.wuyiz.server.controller;

import com.wuyiz.server.pojo.Admin;
import com.wuyiz.server.pojo.VO.LoginVO;
import com.wuyiz.server.service.AdminService;
import com.wuyiz.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created with IntelliJ IDEA
 * @author: suhai
 * @date: 2021-03-07 20:03
 * @description: 登录控制器
 */
@RestController
@Api(tags = "LoginController")
public class LoginController {
    @Autowired
    AdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("login")
    public RespBean login(@RequestBody LoginVO loginVO, HttpServletRequest request) {
        return adminService.login(loginVO.getUsername(), loginVO.getPassword(), loginVO.getCode(), request);
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @PostMapping("admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        Admin admin = adminService.getAdminInfo(principal.getName());
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

    @ApiOperation(value = "退出账户")
    @PostMapping("logout")
    public RespBean logout() {
        return RespBean.success("退出成功");
    }
}
