package dao;

import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// `HelloWorld1
public class UserDAO implements DAO<User> {


    private final static String URL = "jdbc:postgresql://ec2-54-246-85-151.eu-west-1.compute.amazonaws.com:5432/d36isdpvdr1253";
    private final static String NAME = "mgfwnnvgwivxck";
    private final static String PASSWORD = "a7563aa462d5ab9290ca6c5ac6fdb0932487fe94b10adcc13db9690fe0c55f10";

    public static final String INSERT_EMAIL_PSW =
            "INSERT INTO users(email, password) values(?,?);";

    public static final String INSERT_NAME_GENDER_TITLE_PHOTO =
            "UPDATE users SET name = ?, gender = ?, title = ?, photo = ? WHERE id = (select max(id) from users);";

    public static final String SELECT_USER_BY_ID =
            "SELECT name, gender, title, photo FROM users WHERE email =" + "'";

    public static final String SELECT_PHOTO_FROM_USERS =
            "SELECT photo FROM users;";

    public static final String SELECT_NAME_FROM_USERS =
            "SELECT name FROM users;";

    public static final String SELECT_EMAIL_FROM_USERS =
            "SELECT email FROM users;";


//    public static void main(String[] args)  {
//        UserDAO dao = new UserDAO();
//        final List<String> photo = dao.getPhoto();
//        System.out.println(photo);
//    }


    @Override
    public void insertEmailAndPwd(User user) {
        System.out.println(INSERT_EMAIL_PSW);

        try (Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_EMAIL_PSW)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());

            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void insertNameGenderTitlePhoto(User user) {
        System.out.println(INSERT_NAME_GENDER_TITLE_PHOTO);
        try (Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_NAME_GENDER_TITLE_PHOTO)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getGender());
            statement.setString(3, user.getTitle());
            statement.setString(4, user.getPhoto());

            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public User select(String email) {
        try (Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(SELECT_USER_BY_ID + email + "'")) {
            User user = new User();
            user.setName(results.getString(1));
            user.setGender(results.getString(2));
            user.setTitle(results.getString(3));
            user.setPhoto(results.getString(4));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }



    @Override
    public List<String> getPhotos() {
        try (Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(SELECT_PHOTO_FROM_USERS)) {
            List<String> photos = new LinkedList<>();
            while (results.next()) {
                photos.add(results.getString(1));
            }
            return photos;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<String> getNames() {
        try (Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(SELECT_NAME_FROM_USERS)) {
            List<String> names = new LinkedList<>();
            while (results.next()) {
                names.add(results.getString(1));
            }
            return names;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<String> getEmails() {
        try (Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(SELECT_EMAIL_FROM_USERS)) {
            List<String> names = new LinkedList<>();
            while (results.next()) {
                names.add(results.getString(1));
            }
            return names;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
