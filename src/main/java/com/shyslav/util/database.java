package com.shyslav.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Marina on 12.04.2016.
 */
public class database {
    public static Connection connect() throws SQLException {
//        System.out.println(database.class.getResourceAsStream("databaseProp.properties"));
//        Properties props = new Properties();
//        try (InputStream in = database.class.getResourceAsStream("databaseProp.properties")) {
//            props.load(in);
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
        String driver = "com.mysql.jdbc.Driver";
        if (driver != null) {
            System.setProperty("jdbc.drivers", driver);
        }
        String url = "jdbc:mysql://127.0.0.1:3306/happycake?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "password";
        return DriverManager.getConnection(url, username, password);
    }
}
