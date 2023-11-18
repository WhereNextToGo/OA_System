package com.cs_liudi.oa.test;

import com.cs_liudi.oa.utils.MybatisUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TestServlet", urlPatterns = "/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String string = (String)MybatisUtils.excuteQurey(sqlSession -> sqlSession.selectOne("test.sample"));
        request.setAttribute("result",string);
        request.getRequestDispatcher("/test.ftl").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
