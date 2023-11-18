package com.cs_liudi.oa.service;

import com.cs_liudi.oa.dao.LeaveFormDao;
import com.cs_liudi.oa.entity.LeaveForm;
import com.cs_liudi.oa.utils.MybatisUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LeaveFormServiceTest {

    LeaveFormService leaveFormService = new LeaveFormService();

    @Test
    /**
     * 市场部员工请假72小时以上测试用例
     * @throws ParseException
     */
    public void testCreateLeaveForm1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        LeaveForm form = new LeaveForm();
        form.setEmployeeId(8l);
        form.setStartTime(sdf.parse("2020032608"));
        form.setEndTime(sdf.parse("2020040118"));
        form.setFormType(1);
        form.setReason("市场部员工请假单（72小时以上）");
        form.setCreateTime(new Date());
        LeaveForm savedForm = leaveFormService.createLeaveForm(form);
        System.out.println(savedForm.getFormId());
    }
    @Test
    //市场部员工请假单（72小时以内）
    public void testCreateLeaveForm2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        LeaveForm form = new LeaveForm();
        form.setEmployeeId(8l);
        form.setStartTime(sdf.parse("2020032608"));
        form.setEndTime(sdf.parse("2020032718"));
        form.setFormType(1);
        form.setReason("市场部员工请假单（72小时以内）");
        form.setCreateTime(new Date());
        LeaveForm savedForm = leaveFormService.createLeaveForm(form);
        System.out.println(savedForm.getFormId());
    }
    @Test
    public void testCreateLeaveForm3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        LeaveForm form = new LeaveForm();
        form.setEmployeeId(2l);
        form.setStartTime(sdf.parse("2020032608"));
        form.setEndTime(sdf.parse("2020040118"));
        form.setFormType(1);
        form.setReason("研发部部门经理请假单");
        form.setCreateTime(new Date());
        LeaveForm savedForm = leaveFormService.createLeaveForm(form);
        System.out.println(savedForm.getFormId());
    }
    @Test
    public void testCreateLeaveForm4() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        LeaveForm form = new LeaveForm();
        form.setEmployeeId(1l);
        form.setStartTime(sdf.parse("2020032608"));
        form.setEndTime(sdf.parse("2020040118"));
        form.setFormType(1);
        form.setReason("总经理请假单");
        form.setCreateTime(new Date());
        LeaveForm savedForm = leaveFormService.createLeaveForm(form);
        System.out.println(savedForm.getFormId());
    }

    @Test
    public void testSelectByParam(){
        MybatisUtils.excuteQurey(sqlSession -> {
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            List<Map> mapList = leaveFormDao.selectByParam("processing", 6l);
            for (Map m : mapList){
                System.out.println(m);
            }
            return mapList;
        });

    }

}