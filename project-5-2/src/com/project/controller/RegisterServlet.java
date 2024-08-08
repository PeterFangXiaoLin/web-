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

@WebServlet(name = "RegisterServlet", urlPatterns = "/register", loadOnStartup = 12)
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        UserDao userDao = new UserDaoImpl();
        boolean add = userDao.add(user);
        if (add) {
            resp.sendRedirect("login.jsp?success=" + URLEncoder.encode("注册成功", "utf-8"));
        } else {
            resp.sendRedirect("register.jsp?error=" + URLEncoder.encode("注册失败", "utf-8"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
