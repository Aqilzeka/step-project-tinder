package org.tinder.project.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {

    private static final long serialVersionUID = 1;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
                if (cookie.getName().equals("%ID%")) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
            }
        }

        resp.sendRedirect("/login");
    }

}
