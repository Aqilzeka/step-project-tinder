package org.tinder.project.service;

import org.tinder.project.dao.UserDAO;
import org.tinder.project.entity.User;

public class MyProfileService {

    UserDAO users;

    public MyProfileService(){
        users = new UserDAO();
    }

    public void add(User user){
        users.add(user);
    }

}
