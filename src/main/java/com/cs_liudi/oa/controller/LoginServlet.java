package com.cs_liudi.oa.controller;

import com.alibaba.fastjson.JSON;
import com.cs_liudi.oa.entity.User;
import com.cs_liudi.oa.service.UserService;
import com.cs_liudi.oa.service.exception.BussinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "LoginServlet", urlPatterns = "/check_login")
public class LoginServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HashMap<String,Object> result = new HashMap<>();
        try{
            User user = userService.checkLogin(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("login_user",user);
            result.put("code",0);
            result.put("message","success");
            result.put("redirect_url","/index");
        }catch (BussinessException ex){
            logger.error(ex.getMessage(),ex);
            result.put("code",ex.getCode());
            result.put("message",ex.getMessage());
        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            result.put("code",ex.getClass());
            result.put("message",ex.getMessage());
        }
        String json = JSON.toJSONString(result);
        response.getWriter().println(json);
    }
}
