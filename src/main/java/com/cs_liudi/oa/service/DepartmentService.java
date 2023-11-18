package com.cs_liudi.oa.service;

import com.cs_liudi.oa.dao.DepartmentDao;
import com.cs_liudi.oa.entity.Department;
import com.cs_liudi.oa.utils.MybatisUtils;

public class DepartmentService {
    public Department selectById(Long departmentId){
        return (Department)MybatisUtils.excuteQurey(sqlSession -> sqlSession.getMapper(DepartmentDao.class).selectById(departmentId));
    }
}
