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

@WebServlet(name = "UpdateServlet", urlPatterns = "/update", loadOnStartup = 10)
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        UserDao userDao = new UserDaoImpl();
        boolean update = userDao.update(user);
        if (update) {
            resp.sendRedirect("list.jsp?success=" + URLEncoder.encode("修改成功", "utf-8"));
        } else {
            resp.sendRedirect("list.jsp?error=" + URLEncoder.encode("修改失败", "utf-8"));
        }
    }
}
