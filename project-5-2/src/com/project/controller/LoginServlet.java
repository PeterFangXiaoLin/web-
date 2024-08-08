package com.project.controller;

import com.project.model.dao.UserDao;
import com.project.model.dao.impl.UserDaoImpl;
import com.project.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginServlet", urlPatterns = "/login", loadOnStartup = 12)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getOneByUsername(username);
        if (user == null) {
            resp.sendRedirect("login.jsp?error=" + URLEncoder.encode("登录失败，用户不存在", "utf-8"));
            return;
        }
        if (!user.getPassword().equals(password)) {
            resp.sendRedirect("login.jsp?error=" + URLEncoder.encode("登录失败，密码错误", "utf-8"));
            return;
        }
        req.getSession().setAttribute("USER", user);
        resp.sendRedirect("list.jsp?success=" + URLEncoder.encode("登录成功", "utf-8"));
    }
}
