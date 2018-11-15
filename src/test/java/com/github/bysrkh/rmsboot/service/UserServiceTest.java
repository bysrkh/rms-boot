package com.github.bysrkh.rmsboot.service;

import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.github.bysrkh.rmsboot.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserServiceImpl();
    }
}
