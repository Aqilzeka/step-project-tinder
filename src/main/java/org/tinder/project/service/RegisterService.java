package org.tinder.project.service;


import org.tinder.project.dao.UserDAO;
import org.tinder.project.entity.User;

public class RegisterService {

    protected UserDAO users;

    public RegisterService(){
        users = new UserDAO();
    }

    public void register(User user){
        users.insertEmailAndPwd(user);
    }

    public boolean check(User user){
        users.read();
        for (User u: users)
            if (u.checkEqual(user))
                return true;

        return false;
    }

}
