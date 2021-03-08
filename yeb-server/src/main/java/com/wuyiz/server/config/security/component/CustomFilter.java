package com.wuyiz.server.config.security.component;

import com.wuyiz.server.pojo.Menu;
import com.wuyiz.server.pojo.Role;
import com.wuyiz.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * @author: suhai
 * @date: 2021-03-09 1:02
 * @description: 权限控制  根据请求的url分析请求所需的角色
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();  //spring自带的路径匹配类

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 查询菜单列表对应的角色
        List<Menu> menuWithRole = menuService.getMenuWithRole();
        for (Menu menu : menuWithRole) {
            // 判断请求 url 与菜单角色是否匹配
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                String[] strings = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(strings);
            }
        }
        // 没匹配的 url 默认登录即可访问 : ROLE_LOGIN 登录即可拥有的角色
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
