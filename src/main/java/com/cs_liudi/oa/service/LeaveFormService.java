package com.cs_liudi.oa.service;

import com.cs_liudi.oa.dao.EmployeeDao;
import com.cs_liudi.oa.dao.LeaveFormDao;
import com.cs_liudi.oa.dao.ProcessFlowDao;
import com.cs_liudi.oa.entity.Employee;
import com.cs_liudi.oa.entity.LeaveForm;
import com.cs_liudi.oa.entity.ProcessFlow;
import com.cs_liudi.oa.utils.MybatisUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class LeaveFormService {
    public LeaveForm createLeaveForm(LeaveForm leaveForm){
        return (LeaveForm)MybatisUtils.excuteInsert(sqlSession -> {
            //1.持久化form表单数据,8级以下员工表单状态为processing,8级(总经理)状态为approved
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.selectById(leaveForm.getEmployeeId());
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            if (employee.getLevel() <= 7) {
                leaveForm.setState("processing");
            } else if (employee.getLevel() == 8) {
                leaveForm.setState("approved");
            }
            leaveFormDao.insert(leaveForm);
            //2.增加第一条流程数据，说明表单已提交，态为complete
            ProcessFlow processFlow1 = new ProcessFlow();
            processFlow1.setFormId(leaveForm.getFormId());
            processFlow1.setOperatorId(employee.getEmployeeId());
            processFlow1.setAction("apply");
            processFlow1.setCreateTime(new Date());
            processFlow1.setOrderNo(1);
            processFlow1.setState("complete");
            processFlow1.setIsLast(0);
            ProcessFlowDao processFlowDao = sqlSession.getMapper(ProcessFlowDao.class);
            processFlowDao.insert(processFlow1);
            //3.分情况创建其余流程数据
            if (employee.getLevel() < 7) {
                Employee dmanager = employeeDao.selectLeader(employee);
                ProcessFlow processFlow2 = new ProcessFlow();
                processFlow2.setFormId(leaveForm.getFormId());
                processFlow2.setOperatorId(dmanager.getEmployeeId());
                processFlow2.setAction("audit");
                processFlow2.setCreateTime(new Date());
                processFlow2.setOrderNo(2);
                processFlow2.setState("processing");
                long diff = leaveForm.getEndTime().getTime() - leaveForm.getStartTime().getTime();
                float htime = diff / (1000 * 60 * 60) * 1f;
                //3.1 7级以下员工，生成部门经理审批任务，请假时间大于36小时,还需生成总经理审批任务
                if (htime >= 72) {
                    processFlow2.setIsLast(0);
                    processFlowDao.insert(processFlow2);
                    Employee manager = employeeDao.selectLeader(dmanager);
                    ProcessFlow processFlow3 = new ProcessFlow();
                    processFlow3.setFormId(leaveForm.getFormId());
                    processFlow3.setOperatorId(manager.getEmployeeId());
                    processFlow3.setAction("audit");
                    processFlow3.setCreateTime(new Date());
                    processFlow3.setOrderNo(3);
                    processFlow3.setState("ready");
                    processFlow3.setIsLast(1);
                    processFlowDao.insert(processFlow3);
                } else {
                    processFlow2.setIsLast(1);
                    processFlowDao.insert(processFlow2);
                }
            } else if (employee.getLevel() == 7) {
                //3.2 7级员工，生成总经理审批任务
                ProcessFlow flow = new ProcessFlow();
                Employee manager = employeeDao.selectLeader(employee);
                flow.setFormId(leaveForm.getFormId());
                flow.setOperatorId(manager.getEmployeeId());
                flow.setAction("audit");
                flow.setCreateTime(new Date());
                flow.setOrderNo(2);
                flow.setState("processing");
                flow.setIsLast(1);
                processFlowDao.insert(flow);
            } else {
                //3.3 8级员工，生成总经理审批任务，系统自动通过
                ProcessFlow flow = new ProcessFlow();
                flow.setFormId(leaveForm.getFormId());
                flow.setOperatorId(employee.getEmployeeId());
                flow.setAction("audit");
                flow.setResult("自动通过");
                flow.setReason("approved");
                flow.setCreateTime(new Date());
                flow.setAuditTime(new Date());
                flow.setOrderNo(2);
                flow.setState("complete");
                flow.setIsLast(1);
                processFlowDao.insert(flow);
            }
            return leaveForm;
        });
    }

    public List<Map> getLeaveFormList(String pfState,Long operatorId){
        return (List<Map>)MybatisUtils.excuteQurey(sqlSession -> {
            LeaveFormDao mapper = sqlSession.getMapper(LeaveFormDao.class);
            List<Map> leaveFormList = mapper.selectByParam(pfState, operatorId);
            return leaveFormList;
        });
    }
}
