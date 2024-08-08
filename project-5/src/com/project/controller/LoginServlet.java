package com.project.controller;

import com.project.model.dao.UserDao;
import com.project.model.dao.daoImpl.UserDaoImpl;
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
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        if (account == null || "".equals(account)) {
            throw new RuntimeException("账号不能为空");
        }
        if (password == null || "".equals(password)) {
            throw new RuntimeException("密码不能为空");
        }

        UserDao userDao = new UserDaoImpl();
        User oneByAccount = userDao.getOneByAccount(account);
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("content-type", "text/html;charset=utf-8");
        if (oneByAccount == null) {
            resp.sendRedirect("failure.jsp?error=" + URLEncoder.encode("登录失败，账户不存在", "utf-8"));
        } else {
            if (oneByAccount.getPassword().equals(password)) {
                resp.sendRedirect("success.jsp?success=" + URLEncoder.encode("登录成功", "utf-8"));
            } else {
                resp.sendRedirect("failure.jsp?error=" + URLEncoder.encode("登录失败，密码错误", "utf-8"));
            }
        }
    }
}
