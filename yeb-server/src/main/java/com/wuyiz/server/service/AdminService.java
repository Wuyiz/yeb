package com.wuyiz.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyiz.server.pojo.Admin;
import com.wuyiz.server.pojo.Role;
import com.wuyiz.server.utils.RespBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
public interface AdminService extends IService<Admin> {

    /**
     * 登录业务逻辑
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 获取当前登录的用户信息
     * @param username
     * @return
     */
    Admin getAdminInfo(String username);

    /**
     * 根据用户id获取角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
