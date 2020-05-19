package org.tinder.project.service;


import org.tinder.project.dao.UserDAO;
import org.tinder.project.entity.User;

public class RegisterService {

    UserDAO users;

    public RegisterService(){
        users = new UserDAO();
    }

    public void register(User user){
        users.insertEmailAndPwd(user);
    }

}
