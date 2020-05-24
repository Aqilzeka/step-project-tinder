package org.tinder.project.dao;

import lombok.extern.log4j.Log4j2;
import org.tinder.project.db.DBConnection;
import org.tinder.project.entity.User;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// `HelloWorld1
@Log4j2
public class UserDAO implements DAO<User> {

    private List<User> users;

    public UserDAO() {
        read();
    }


    public static final String USERS =
            "SELECT * FROM users;";
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
            log.error("Didn't read " + e);
        }
    }

    @Override
    public List<Integer> getAllId() {
        read();
        List<Integer> result = new LinkedList<>();
        users.forEach(user -> result.add(user.getId()));
        return result;
    }

    public void insertEmailAndPwd(User user) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement insertUser = connection.prepareStatement(INSERT_EMAIL_PSW);

            insertUser.setString(1, user.getEmail());
            insertUser.setString(2, user.getPassword());

            insertUser.executeUpdate();
            users.add(user);
        } catch (SQLException e) {
            log.error("Can't added to users", e);
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
        } catch (SQLException e) {
            log.error("Can't added to users", e);
        }
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
    public User get(int id) {
        return stream().filter(user -> user.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }

}
