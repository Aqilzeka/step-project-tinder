import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;

public class
ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);
        ServletContextHandler handler = new ServletContextHandler();
        TemplateEngine engine = TemplateEngine.folder("./content");


        handler.addServlet(new ServletHolder(new LikeServlet(engine)), "/like/*");
        handler.addServlet(new ServletHolder(new StaticServlet("bootstrap")),"/bootstrap/0");
        handler.addServlet(new ServletHolder(new StaticServlet("css")),"/css/*");
        handler.addServlet(new ServletHolder(new RegisterServlet()), "/register");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login");
        handler.addServlet(new ServletHolder(new MyProfileServlet()), "/myProfile");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
