package servlet;

import entity.User;
import service.MyProfileService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1;
    private final MyProfileService myProfileService = new MyProfileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Path path = Paths.get("./content/profile.html");
        ServletOutputStream outputStream = resp.getOutputStream();
        Files.copy(path, outputStream);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String title = req.getParameter("title");
        String photo = req.getParameter("photo");
        System.out.println(name);
        System.out.println(gender);
        System.out.println(title);
        System.out.println(photo);
        myProfileService.add(new User(name, gender, title, photo));
        resp.sendRedirect("/login");
    }
}
