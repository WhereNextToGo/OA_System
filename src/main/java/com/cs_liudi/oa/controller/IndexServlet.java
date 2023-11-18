package com.cs_liudi.oa.controller;

import com.cs_liudi.oa.dao.EmployeeDao;
import com.cs_liudi.oa.entity.Department;
import com.cs_liudi.oa.entity.Employee;
import com.cs_liudi.oa.entity.Node;
import com.cs_liudi.oa.entity.User;
import com.cs_liudi.oa.service.DepartmentService;
import com.cs_liudi.oa.service.EmployeeService;
import com.cs_liudi.oa.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    private UserService userService = new UserService();
    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("login_user");
            List<Node> nodes = userService.selectNodeByUserId(user.getUserId());
            session.setAttribute("node_list", nodes);
            Employee employee = employeeService.selectById(user.getEmployeeId());
            session.setAttribute("current_employee", employee);
            Department department = departmentService.selectById(employee.getDepartmentId());
            session.setAttribute("current_department", department);
            request.getRequestDispatcher("/index.ftl").forward(request, response);
        }catch (Exception e){
            HttpSession session = request.getSession();
            session.setAttribute("error",e.getClass().getSimpleName());
            request.getRequestDispatcher("/error.ftl").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
