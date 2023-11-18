package com.cs_liudi.oa.dao;

import com.cs_liudi.oa.entity.LeaveForm;
import com.cs_liudi.oa.utils.MybatisUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class LeaveFormDaoTest {

    @Test
    public void testInsert() {
        MybatisUtils.excuteInsert(sqlSession -> {
            LeaveFormDao mapper = sqlSession.getMapper(LeaveFormDao.class);
            LeaveForm form = new LeaveForm();
            form.setEmployeeId(4l);
            form.setFormType(1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = null;
            Date endTime = null;
            try {
                startTime = simpleDateFormat.parse("2020-03-25 08:00:00");
                endTime = simpleDateFormat.parse("2020-04-01 18:00:00");
            }catch (ParseException e) {
                e.printStackTrace();
            }
            form.setStartTime(startTime);
            form.setEndTime(endTime);
            form.setReason("回家探亲");
            form.setCreateTime(new Date());
            form.setState("processing");
            Integer num = mapper.insert(form);
            System.out.println(num);
            System.out.println(form.getFormId());
            return null;
        });
    }
}