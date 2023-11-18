package com.cs_liudi.oa.dao;

import com.cs_liudi.oa.entity.Department;

public interface DepartmentDao {
    public Department selectById(Long departmentId);
}
