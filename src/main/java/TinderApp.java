import org.tinder.project.heroku.HerokuEnv;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinder.project.migrations.DbSetup;
import org.tinder.project.service.LikeService;
import org.tinder.project.service.LikedService;
import org.tinder.project.service.LoginService;
import org.tinder.project.service.MessageService;
import org.tinder.project.servlet.*;


/**
 *
 * http://localhost:5000/login
 *
 * */

public class TinderApp {

//    private final static String URL = "jdbc:postgresql://ec2-54-246-85-151.eu-west-1.compute.amazonaws.com:5432/d36isdpvdr1253";
//    private final static String NAME = "mgfwnnvgwivxck";
//    private final static String PASSWORD = "a7563aa462d5ab9290ca6c5ac6fdb0932487fe94b10adcc13db9690fe0c55f10";

    public static void main(String[] args) throws Exception {
        Server server = new Server(HerokuEnv.port());
        ServletContextHandler handler = new ServletContextHandler();

     //   DbSetup.migrate(URL,NAME,PASSWORD);


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
        handler.addServlet(new ServletHolder(new LogOutServlet()),"/logOut");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
