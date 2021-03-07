package com.wuyiz.server.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }
}
