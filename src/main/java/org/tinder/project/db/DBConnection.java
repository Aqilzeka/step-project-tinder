package org.tinder.project.db;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
public class DBConnection {

    private final static String URL = "jdbc:postgresql://ec2-34-232-147-86.compute-1.amazonaws.com:5432/d79pqhfsbv3cgk";
    private final static String NAME = "gbygpgpuvrardi";
    private final static String PASSWORD = "9f841d1e93fa7f69cbd0a55098f98ae1ef5d081ad9ba0cf84688bedd00261b8a";

    private static Connection connection;

    private DBConnection(){
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL,NAME,PASSWORD);
            } catch (SQLException e) {
                log.error("Can't connected to database");
            }
        }
        return connection;
    }
}
