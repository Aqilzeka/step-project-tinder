package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final static String URL = "jdbc:postgresql://ec2-54-246-85-151.eu-west-1.compute.amazonaws.com:5432/d36isdpvdr1253";
    private final static String NAME = "mgfwnnvgwivxck";
    private final static String PASSWORD = "a7563aa462d5ab9290ca6c5ac6fdb0932487fe94b10adcc13db9690fe0c55f10";

    private static Connection connection;

    private DBConnection(){
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL,NAME,PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException("Can't connected to database");
            }
        }
        return connection;
    }
}
