package org.tinder.project.servlet;

import lombok.extern.log4j.Log4j2;
import org.tinder.project.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService loginService = new LoginService();
        loginService.setLogged(false);
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
                if (cookie.getName().equals("%ID%")) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
        log.info("Log outed");

        resp.sendRedirect("/login");
    }

}
