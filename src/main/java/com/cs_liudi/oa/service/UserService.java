package com.cs_liudi.oa.service;

import com.cs_liudi.oa.dao.RbacDao;
import com.cs_liudi.oa.dao.UserDao;
import com.cs_liudi.oa.entity.Node;
import com.cs_liudi.oa.entity.User;
import com.cs_liudi.oa.service.exception.BussinessException;
import com.cs_liudi.oa.utils.MD5Utils;

import java.util.List;

public class UserService {
    private UserDao userDAO = new UserDao();
    private RbacDao rbacDao = new RbacDao();
    public User checkLogin(String username, String password){
        User user = userDAO.selectByUsername(username);
        if (user == null){
            throw new BussinessException("L001","用户名不存在");
        }
        String md5 = MD5Utils.md5Digist(password,user.getSalt());
        if (!md5.equals(user.getPassword())){
            throw new BussinessException("L002","密码错误");
        }
        return user;
    }

    public List<Node> selectNodeByUserId(Long userId){
        List<Node> nodes = rbacDao.selectNodeByUserId(userId);
        return nodes;
    }
}
