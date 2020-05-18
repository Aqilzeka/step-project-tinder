package servlet;

import entity.User;
import service.LoginService;

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

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1;
    private final LoginService loginService;

    public LoginServlet(LoginService loginService) {
        this.loginService = loginService;
    }


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
            resp.sendRedirect("/like");
        } catch (Exception e) {
            resp.sendRedirect("/login");
        }
    }

}
