package servlet;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.stream.Collectors;

public class MyProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1;
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = new BufferedReader(new FileReader(new File("content/profile.html"))).lines()
                .collect(Collectors.joining("\n"));

        try (PrintWriter writer = resp.getWriter()) {
            writer.write(content);
        }
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
        userDAO.insertNameGenderTitlePhoto(new User(name,gender,title,photo));
        resp.sendRedirect("/like");
    }
}
