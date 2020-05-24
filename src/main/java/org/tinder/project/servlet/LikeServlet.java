package org.tinder.project.servlet;

import lombok.extern.log4j.Log4j2;
import org.tinder.project.entity.User;
import org.tinder.project.service.LikeService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Log4j2
public class LikeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final LikeService service = new LikeService();
    private User user;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("%ID%"))
                service.setLocalId(Integer.parseInt(cookie.getValue()));

            try {
                if (user.getId() == service.getLocalId())
                    user = service.getNext(user.getId());
            } catch (Exception e){
                user = service.getFirst();
                log.info("All users liked");
                resp.sendRedirect("/liked");
            }



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
            user = service.getFirst();
            log.info("All users liked");
            resp.sendRedirect("/liked");
        }
    }
}
