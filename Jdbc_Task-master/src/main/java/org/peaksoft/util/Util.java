package org.peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // TODO: 27.09.2023   реализуйте настройку соеденения с БД
    private static final String USR = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "nurjigit01";


    public static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(USR, USER_NAME, PASSWORD);
            System.out.println("Success connected to the database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
