package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@ContextConfiguration("classpath*:/applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private  UserDao userDao;

    @Test
    public void testFindUserByUserName(){
        User admin = userDao.findUserByUserName("admin");
        assertEquals(admin.getUserName(), "admin");
    }
}
