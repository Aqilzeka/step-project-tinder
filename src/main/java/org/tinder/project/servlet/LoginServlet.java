package org.tinder.project.servlet;

import lombok.extern.log4j.Log4j2;
import org.tinder.project.entity.User;
import org.tinder.project.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!loginService.isLogged()) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("%ID%")) {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    }
                }
            }

        }

        Path path = Paths.get("./content/login.html");
        ServletOutputStream outputStream = resp.getOutputStream();
        Files.copy(path, outputStream);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            int id = loginService.check(new User(email, password));
            resp.addCookie(new Cookie("%ID%", String.valueOf(id)));
            LikeServlet likeServlet = new LikeServlet();
            likeServlet.doGet(req,resp);
            resp.sendRedirect("/like");
        } catch (Exception e) {
            log.warn("Yor password or email isn't correct");
            resp.sendRedirect("/login");
        }
    }

}
