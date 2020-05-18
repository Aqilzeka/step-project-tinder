package servlet;

import entity.User;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MessageServlet extends HttpServlet {
    private int senderId;
    private int receiverId;
    private final MessageService service;


    public MessageServlet(MessageService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie: cookies)
            if (cookie.getName().equals("%ID%"))
                senderId = Integer.parseInt(cookie.getValue());
        final String replace = req.getParameter("id");
        System.out.println(replace);
        receiverId = Integer.parseInt(replace);

        TemplateEngine engine = new TemplateEngine("./content");
        User user = service.getUser(receiverId);
        List<String> formattedMessages = service.getFormattedMessages(senderId,receiverId);
        HashMap<String,Object> data = new HashMap<>();
        data.put("userTo",user.getName());
        if (!formattedMessages.isEmpty())
            data.put("messages",formattedMessages);
        else
            data.put("messages",new LinkedList<Integer>());
        engine.render("chat.ftl",data,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");

        service.write(senderId, receiverId, message);
        resp.sendRedirect(String.format("/messages?id=%d", receiverId));
    }
}
