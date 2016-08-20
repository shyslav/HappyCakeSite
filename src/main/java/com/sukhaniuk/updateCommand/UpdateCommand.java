package com.sukhaniuk.updateCommand;

import com.shyslav.util.DatabaseConnection;

import java.sql.SQLException;

/**
 * Created by Shyshkin Vladyslav on 05.05.2016.
 */
public class UpdateCommand {
    private static DatabaseConnection db = new DatabaseConnection();
    public static String updateTable(String tableName, String [] args, String [] where)
    {
        String query = "update "+ tableName +" set " +String.join(",",args) + " where " + String.join(" and ",where);
        System.out.println(query);
        db.openConnection();
        try {
            db.st.execute(query);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Что-то пошло не так" +ex;
        }         finally
        {
            db.closeConnection();
        }
        db.closeConnection();
        return "ok";
    }
}
