package service;


import dao.UserDAO;
import entity.User;

public class RegisterService {

    UserDAO users;

    public RegisterService(){
        users = new UserDAO();
    }

    public void register(User user){
        users.insertEmailAndPwd(user);
    }

}
