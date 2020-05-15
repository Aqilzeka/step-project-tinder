package servlet;

import dao.UserDAO;
import entity.User;
import service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1;
    private final RegisterService registerService = new RegisterService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String content = new BufferedReader(new FileReader(new File("content/register.html"))).lines()
//                .collect(Collectors.joining("\n"));
//
//        try (PrintWriter writer = resp.getWriter()) {
//            writer.write(content);
//        }

        Path path = Paths.get("./content/register.html");
        ServletOutputStream outputStream = resp.getOutputStream();
        Files.copy(path,outputStream);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        registerService.register(new User(email,password));

        resp.sendRedirect("/myProfile");
    }


}
