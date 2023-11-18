package com.cs_liudi.oa.dao;

import com.cs_liudi.oa.entity.User;
import com.cs_liudi.oa.utils.MybatisUtils;

public class UserDao {

    public User selectByUsername(String username){
        User user = (User)MybatisUtils.excuteQurey(sqlSession -> sqlSession.selectOne("usermapper.selectByUsername",username));
        return user;
    }
}
