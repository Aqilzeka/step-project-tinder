package org.tinder.project;

import org.tinder.project.heroku.HerokuEnv;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
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
    public static void main(String[] args) throws Exception {
        Server server = new Server(HerokuEnv.port());
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
        handler.addServlet(new ServletHolder(new LogOutServlet()),"/logOut");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
