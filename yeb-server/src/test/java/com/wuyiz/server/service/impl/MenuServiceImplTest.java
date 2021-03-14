package com.wuyiz.server.service.impl;


import com.wuyiz.server.YebApplicaton;
import com.wuyiz.server.service.EmployeeService;
import com.wuyiz.server.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YebApplicaton.class)
public class MenuServiceImplTest {
    @Autowired
    MenuService menuService;
    @Autowired
    EmployeeService employeeService;

    @Test
    public void getMenuWithRole() {
        //System.out.println(menuService.getMenuWithRole());
        System.out.println(new Date(System.currentTimeMillis() + 60 * 1000));
    }

    @Test
    public void maxWorkID() {
        //System.out.println(menuService.getMenuWithRole());
        System.out.println(employeeService.maxWorkID());
    }
}