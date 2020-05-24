package org.tinder.project.service;

import org.tinder.project.dao.UserDAO;
import org.tinder.project.entity.User;

public class LoginService {

    protected UserDAO users;
    protected boolean isLogged;

    public LoginService(){
        isLogged = false;
        users = new UserDAO();
    }

    public int check(User user){
        users.read();
        for (User u: users){
            if (u.checkEqual(user)){
                isLogged = true;
                return u.getId();
            }
        }
        throw new RuntimeException("Login failed");
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
