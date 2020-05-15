package service;

import dao.UserDAO;
import entity.User;

public class MyProfileService {

    UserDAO users;

    public MyProfileService(){
        users = new UserDAO();
    }

    public void add(User user){
        users.add(user);
    }

}
