package org.tinder.project.dao;

import org.tinder.project.entity.User;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// `HelloWorld1
public class UserDAO implements DAO<User> {

    private List<User> users;

    public UserDAO() {
        read();
    }


    public static final String USERS = "SELECT * FROM users;";
    public static final String DELETE = "DELETE FROM users";


    public static final String INSERT_EMAIL_PSW =
            "INSERT INTO users(email, password) values(?,?);";

    public static final String INSERT_NAME_GENDER_TITLE_PHOTO =
            "UPDATE users SET name = ?, gender = ?, title = ?, photo = ? WHERE id = (select max(id) from users);";


    @Override
    public void read() {
        users = new LinkedList<>();
        try  {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("gender"),
                        resultSet.getString("title"),
                        resultSet.getString("photo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {

        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.executeUpdate();
            users = new LinkedList<>();
        } catch (SQLException e) {
            throw new RuntimeException("Can't deleted from user", e);
        }

    }

    public void insertEmailAndPwd(User user) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement insertUser = connection.prepareStatement(INSERT_EMAIL_PSW);

            insertUser.setString(1, user.getEmail());
            insertUser.setString(2, user.getPassword());

            System.out.println(insertUser);
            insertUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can't added to users", e);
        }
    }
    @Override
    public void add(User user) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement insertUser = connection.prepareStatement(INSERT_NAME_GENDER_TITLE_PHOTO);
            insertUser.setString(1, user.getName());
            insertUser.setString(2, user.getGender());
            insertUser.setString(3, user.getTitle());
            insertUser.setString(4, user.getImgURL());

            System.out.println(insertUser);
            insertUser.executeUpdate();
            users.add(user);

        } catch (SQLException e) {
            throw new RuntimeException("Can't added to users", e);
        }
    }

    @Override
    public List<Integer> getAllId() {
        read();
        List<Integer> result = new LinkedList<>();
        users.forEach(user -> result.add(user.getId()));
        return result;
    }

    @Override
    public List<User> getDatabase() {
        return users;
    }

    @Override
    public Stream<User> stream() {
        return users.stream();
    }

    @Override
    public Integer size() {
        return users.size();
    }

    @Override
    public User get(int id) {
        return stream().filter(user -> user.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}
