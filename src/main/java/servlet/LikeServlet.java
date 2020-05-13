package servlet;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LikeServlet extends HttpServlet {
    private TemplateEngine engine;
    private static final long serialVersionUID = 1;
    private final UserDAO userDAO = new UserDAO();
    private final User user = new User();

    public LikeServlet(TemplateEngine engine) {
        this.engine = engine;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<String> photo = userDAO.getPhotos();
        final List<String> name = userDAO.getNames();
        user.setName(name.get(0));
        user.setPhoto(photo.get(0));

        System.out.println(photo);
        System.out.println(name);


        List<User> users = Arrays.asList(user);
        HashMap<String, Object> data = new HashMap<>();
        data.put("users", users);

        engine.render("like.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
