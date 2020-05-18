import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.*;
import servlet.*;

public class
TinderApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);
        ServletContextHandler handler = new ServletContextHandler();

        LoginService loginService = new LoginService();
        LikeService likeService = new LikeService();
        LikedService likedService = new LikedService();
        MessageService messageService = new MessageService();


        handler.addServlet(new ServletHolder(new StaticServlet("bootstrap")),"/bootstrap/*");
        handler.addServlet(new ServletHolder(new StaticServlet("css")),"/css/*");
        handler.addServlet(new ServletHolder(new StaticServlet("style")),"/style/*");
        handler.addServlet(new ServletHolder(new StaticServlet("abc")),"/abc/*");



        handler.addServlet(new ServletHolder(new RegisterServlet()), "/register/*");
        handler.addServlet(new ServletHolder(new LoginServlet(loginService)), "/login/*");
        handler.addServlet(new ServletHolder(new MyProfileServlet()), "/myProfile/*");
        handler.addServlet(new ServletHolder(new LikeServlet(likeService)), "/like/*");
        handler.addServlet(new ServletHolder(new MessageServlet(messageService)),"/messages/*");
        handler.addServlet(new ServletHolder(new LikedServlet(likedService)),"/liked/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
