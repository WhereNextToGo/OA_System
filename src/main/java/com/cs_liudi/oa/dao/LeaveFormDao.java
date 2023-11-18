package com.cs_liudi.oa.dao;

import com.cs_liudi.oa.entity.LeaveForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LeaveFormDao {
    public Integer insert(LeaveForm leaveForm);
    public List<Map> selectByParam(@Param("pf_state") String state,@Param("pf_operator_id")Long operatorId);
}
