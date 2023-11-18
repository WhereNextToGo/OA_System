package com.cs_liudi.oa.controller;

import com.alibaba.fastjson.JSON;
import com.cs_liudi.oa.entity.LeaveForm;
import com.cs_liudi.oa.entity.User;
import com.cs_liudi.oa.service.LeaveFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LeaveFormServlet", value = "/leave/*")
public class LeaveFormServlet extends HttpServlet {
    private LeaveFormService leaveFormService = new LeaveFormService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String uri = request.getRequestURI();
        String subUri = uri.substring(uri.lastIndexOf("/") + 1);
        if (subUri.equals("create")){
            this.create(request,response);
        }else if(subUri.equals("list")){
            this.getLeaveFormList(request,response);
        }
    }

    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Logger logger = LoggerFactory.getLogger(LeaveFormServlet.class);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("login_user");
        String formType = request.getParameter("formType");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");
        String reason = request.getParameter("reason");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
        HashMap map = new HashMap();
        try {
            LeaveForm leaveForm = new LeaveForm();
            leaveForm.setEmployeeId(user.getEmployeeId());
            leaveForm.setFormType(Integer.parseInt(formType));
            leaveForm.setReason(reason);
            leaveForm.setStartTime(sdf.parse(startTimeStr));
            leaveForm.setEndTime(sdf.parse(endTimeStr));
            leaveForm.setCreateTime(new Date());
            leaveFormService.createLeaveForm(leaveForm);
            map.put("code","0");
            map.put("message","success");
        } catch (Exception e) {
            logger.error("请假申请异常");
            map.put("code",e.getClass().getSimpleName());
            map.put("message",e.getMessage());
        }
        String jsonString = JSON.toJSONString(map);
        response.getWriter().println(jsonString);
    }
    public void getLeaveFormList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User)request.getSession().getAttribute("login_user");
        List<Map> formList = leaveFormService.getLeaveFormList("processing", user.getEmployeeId());
        Map map = new HashMap();
        map.put("code","0");
        map.put("msg","");
        map.put("count",formList.size());
        map.put("data",formList);
        String json = JSON.toJSONString(map);
        response.getWriter().println(json);
    }

}
