package com.cs_liudi.oa.dao;

import com.cs_liudi.oa.entity.LeaveForm;
import com.cs_liudi.oa.entity.ProcessFlow;
import com.cs_liudi.oa.utils.MybatisUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ProcessFlowDaoTest {

    @Test
    public void testInsert() {
        MybatisUtils.excuteInsert(sqlSession -> {
            ProcessFlowDao mapper = sqlSession.getMapper(ProcessFlowDao.class);
            ProcessFlow flow = new ProcessFlow();
            flow.setFormId(3l);
            flow.setOperatorId(2l);
            flow.setAction("audit");
//            flow.setResult("approved");
//            flow.setReason ("同意");
            flow.setCreateTime (new Date ());
//            flow.setAuditTime (new Date ());
            flow.setOrderNo(1);
            flow.setState("ready");
            flow.setIsLast(1);
            Integer num = mapper.insert(flow);
            System.out.println(num);
            System.out.println(flow.getProcessId());
            return null;
        });
    }
}