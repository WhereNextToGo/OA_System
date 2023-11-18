package com.cs_liudi.oa.service;

import com.cs_liudi.oa.entity.Node;
import com.cs_liudi.oa.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService = new UserService();
    @Test
    public void checkLogin1() {
        userService.checkLogin("sdf","sdfssdf");
    }

    @Test
    public void checkLogin2() {
        userService.checkLogin("m8","sdfssdf");
    }

    @Test
    public void checkLogin3() {
        User user = userService.checkLogin("m8", "test");
        System.out.println(user);
    }
    @Test
    public void testSelectNodeByUserId() {
        List<Node> nodes = userService.selectNodeByUserId(1l);
        for (Node n:nodes){
            System.out.println(n);
        }
    }
}