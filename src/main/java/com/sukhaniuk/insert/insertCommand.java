package com.sukhaniuk.insert;

import com.shyslav.util.DatabaseConnection;

import java.sql.SQLException;

/**
 * Created by Shyshkin Vladyslav on 05.05.2016.
 */
public class insertCommand {
    private static DatabaseConnection db = new DatabaseConnection();
    public static String insert(String tablename, String [] values, String [] rows)
    {
        db.getConnection();
        String command = "insert into "+tablename+"("+String.join(",",rows)+") values ('"+String.join("','",values)+"')";
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            System.out.println(ex);
            return "Группа не добавлена по причине:"+ex;
        } finally {
            db.closeConnection();
        }
        return "done";
    }
}
