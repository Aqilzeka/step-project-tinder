package servlet;

import dao.UserDAO;
import entity.Like;
import entity.User;
import service.LikeService;
import service.LikedService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LikeServlet extends HttpServlet {

    private static final long serialVersionUID = 1;
    private final LikeService service;
    private User user;


    public LikeServlet(LikeService service) {
        this.service = service;
        user = service.getFirst();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("%ID%"))
                service.setLocalId(Integer.parseInt(cookie.getValue()));
        if (user.getId() == service.getLocalId())
            user = service.getNext(user.getId());

        TemplateEngine engine = new TemplateEngine("./content");
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("name", user.getName());
        data.put("title",user.getTitle());
        data.put("imgURL", user.getImgURL());
        engine.render("like.ftl", data, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String like = req.getParameter("like");
        if (like != null) service.like(Integer.parseInt(like));
        try {
            user = service.getNext(user.getId());
            resp.sendRedirect("/like");
        } catch (Exception e){
            resp.sendRedirect("/liked");
        }
    }
}
