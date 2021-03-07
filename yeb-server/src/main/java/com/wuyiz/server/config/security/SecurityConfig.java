package com.wuyiz.server.config.security;

import com.wuyiz.server.pojo.Admin;
import com.wuyiz.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA
 * @author: suhai
 * @date: 2021-03-07 23:27
 * @description: Security配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    AdminService adminService;
    @Autowired
    RestAuthorizationEntryPoint restAuthorizationEntryPoint;    // 自定义的尚未登陆或token失效的结果页面
    @Autowired
    RestfulAccessDeniedHandler restfulAccessDeniedHandler;      // 自定义的没有权限的结果页面

    /**
     * 让springSecurity走自定义的userDetailsService方法
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    /**
     * springSecurity的完整配置方法
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 使用JWT令牌验证，不存在cookie跨站请求伪造
        http.csrf().disable()
                // 基于token实现，不需要session保存信息
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 允许登录访问
                .antMatchers("/login", "/logout")
                .permitAll()
                // 除了上面的，所有请求都需要认证
                .anyRequest()
                .authenticated()
                .and()
                // 禁用缓存
                .headers()
                .cacheControl();

        // 添加jwt登录授权过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);
    }

    /**
     * 重新了UserDetailsService，用我们自己的业务逻辑去查询用户名
     * @return
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return username -> {
            Admin admin = adminService.getAdminInfo(username);
            if (null != admin) {
                return admin;
            }
            return null;
        };
    }

    /**
     * springSecurity自带的加解密对象
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * jwt 授权登录过滤器 构建对象
     * @return
     */
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }
}
