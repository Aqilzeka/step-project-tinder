package service;

import dao.UserDAO;
import entity.User;

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
}
