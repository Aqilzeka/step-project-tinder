package servlet;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.stream.Collectors;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1;
    UserDAO userDAO = new UserDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = new BufferedReader(new FileReader(new File("content/login.html"))).lines()
                .collect(Collectors.joining("\n"));

        try (PrintWriter writer = resp.getWriter()) {
            writer.write(content);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        System.out.println(email);
        if (userDAO.getEmails().contains(email)) resp.sendRedirect("/like");
        else resp.sendRedirect("/register");
    }

}
