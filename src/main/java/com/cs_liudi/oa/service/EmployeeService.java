package com.cs_liudi.oa.service;

import com.cs_liudi.oa.dao.DepartmentDao;
import com.cs_liudi.oa.dao.EmployeeDao;
import com.cs_liudi.oa.entity.Department;
import com.cs_liudi.oa.entity.Employee;
import com.cs_liudi.oa.utils.MybatisUtils;

public class EmployeeService {
    public Employee selectById(Long employeeId){
        return (Employee) MybatisUtils.excuteQurey(sqlSession -> sqlSession.getMapper(EmployeeDao.class).selectById(employeeId));
    }
}
