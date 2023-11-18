package com.cs_liudi.oa.dao;

import com.cs_liudi.oa.entity.Node;
import com.cs_liudi.oa.utils.MybatisUtils;

import java.util.List;

public class RbacDao {
    public List<Node> selectNodeByUserId(Long userId){
        List<Node> nodes = (List<Node>)MybatisUtils.excuteQurey(sqlSession -> sqlSession.selectList("rbacmapper.selectNodeByUserId",userId));
        return nodes;
    }
}
