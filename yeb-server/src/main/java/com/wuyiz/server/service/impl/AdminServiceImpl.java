package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.config.security.component.JwtTokenUtil;
import com.wuyiz.server.mapper.AdminMapper;
import com.wuyiz.server.mapper.RoleMapper;
import com.wuyiz.server.pojo.Admin;
import com.wuyiz.server.pojo.Role;
import com.wuyiz.server.service.AdminService;
import com.wuyiz.server.utils.RespBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    AdminMapper adminMapper;
    @Resource
    RoleMapper roleMapper;

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isBlank(code) || !StringUtils.equalsIgnoreCase(captcha, code)) {
            return RespBean.error("验证码输入错误，请重新输入！");
        }
        /**
         * 通过loadUserByUsername方法查询用户信息
         */
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //判断用户是否存在或密码是否正确
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户不存在或密码不正确");
        }

        /**
         * 将登录用户信息动态更新到security中，可以用principal对象获取用户信息
         *
         * UsernamePasswordAuthenticationToken:
         *      UsernamePasswordAuthenticationToken继承了AbstractAuthenticationtoken抽象类,
         *      其主要与AbstractAuthenticationtoken的区别是针对用户名和密码约定进行了一定的封装,
         *      将username复制到了principal，而将password赋值到了credentials，最后传入权限列表
         *
         * 此处传入第二参数为密码，但是密码不能保存在服务器，所以使用null传入
         */
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token并返回
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("token", jwtTokenUtil.generateToken(userDetails));
        return RespBean.success("登录成功", tokenMap);
    }

    @Override
    public Admin getAdminInfo(String username) {
        return adminMapper.selectOne(
                new QueryWrapper<Admin>()
                        .eq("username", username).eq("enabled", true));
    }

    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }
}
