package org.tinder.project.service;

import org.tinder.project.dao.UserDAO;
import org.tinder.project.entity.User;

public class LogOutService {

    protected UserDAO users;
    protected boolean isLogOuted;

    public LogOutService(UserDAO users, boolean isLogged) {
        this.users = new UserDAO();
        this.isLogOuted = false;
    }

    public int check(){
        users.read();
        for (User user: users){
            if (user.checkEqual(user)){
                isLogOuted = true;
                return user.getId();
            }
        }
        throw new RuntimeException("Log Out Failed");
    }

    public boolean isLogOuted() {
        return isLogOuted;
    }
}
