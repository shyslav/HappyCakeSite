package com.shyslav.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private Connection con;
    public Statement st;
    public ResultSet rs;

    public void getConnection()
    {
        Properties props = new Properties();
        try (InputStream in = DatabaseConnection.class.getResourceAsStream("database.properties")) {
            props.load(in);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        try{
            Class.forName(props.getProperty("jdbc.drivers"));
            con = DriverManager.getConnection(props.getProperty("jdbc.url"),props.getProperty("jdbc.username"),props.getProperty("jdbc.password"));
            st = con.createStatement();
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            System.out.println("Error"+ex);
        }
    }
    public void closeConnection()
    {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}