package org.tinder.project.servlet;

import org.tinder.project.entity.User;
import org.tinder.project.service.LikedService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LikedServlet extends HttpServlet {
    private static final long serialVersionUID = 1;
    protected int localId;
    protected LikedService service = new LikedService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("%ID%"))
                localId = Integer.parseInt(cookie.getValue());
        }
        List<User> likedUsers = service.getLikedUsers(localId);
        //likedUsers.forEach(System.out::println);
        TemplateEngine engine = new TemplateEngine("./content");
        HashMap<String, Object> data = new HashMap<>();
        data.put("likedPeoples", likedUsers);
        engine.render("people-list.ftl", data, resp);
    }
}
