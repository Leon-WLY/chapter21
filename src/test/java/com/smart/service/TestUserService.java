package com.smart.service;
import static org.junit.Assert.*;
import com.smart.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
//import org.testng.annotations.Test;

import java.util.Date;

//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext.xml"})//启动Spring容器
public class TestUserService //extends AbstractTransactionalTestNGSpringContextTests
{
    @Autowired
    private  UserService userService;

    @Test
    public  void hasMatchUser(){
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void findUserByUserName(){
        User admin = userService.findUserByUserName("admin");
        assertEquals(admin.getUserName(), "admin");
    }

    @Test
    public void loginSuccess(){
        User admin = userService.findUserByUserName("admin");
        admin.setLastIp("192.168.12.7");
        admin.setLastVisit(new Date());
        userService.loginSuccess(admin);
    }
}
