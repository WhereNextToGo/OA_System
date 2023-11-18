package com.cs_liudi.oa.dao;

import com.cs_liudi.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeDao {
    public Employee selectById(Long employeeId);
    public Employee selectLeader(@Param("emp") Employee employee);
}
