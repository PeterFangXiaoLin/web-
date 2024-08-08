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

@WebServlet(name = "DeleteServlet", urlPatterns = "/del", loadOnStartup = 10)
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        User loginUser = (User) req.getSession().getAttribute("USER");
        if (loginUser != null && loginUser.getId() != id) {
            resp.sendRedirect("list.jsp?error=" + URLEncoder.encode("删除失败，你无权限", "utf-8"));
            return;
        }
        req.getSession().removeAttribute("USER");
        UserDao userDao = new UserDaoImpl();
        boolean delete = userDao.delete(id);
        if (delete) {
            resp.sendRedirect("list.jsp?success=" + URLEncoder.encode("删除成功", "utf-8"));
        } else {
            resp.sendRedirect("list.jsp?error=" + URLEncoder.encode("删除失败", "utf-8"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
