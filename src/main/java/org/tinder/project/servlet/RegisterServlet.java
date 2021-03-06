package org.tinder.project.servlet;

import lombok.extern.log4j.Log4j2;
import org.tinder.project.entity.User;
import org.tinder.project.service.LoginService;
import org.tinder.project.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Log4j2
public class RegisterServlet extends HttpServlet {

    private final RegisterService registerService = new RegisterService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Path path = Paths.get("./content/register.html");
        ServletOutputStream outputStream = resp.getOutputStream();
        Files.copy(path,outputStream);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User(email,password);

            if (!registerService.check(user)){
                registerService.register(user);
                log.info("Registered");
                resp.sendRedirect("/myProfile");
            } else {
            log.warn("This account already used");
            resp.sendRedirect("/register");
        }
        }



}
