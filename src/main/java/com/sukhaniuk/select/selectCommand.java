package com.sukhaniuk.select;

import com.shyslav.util.database;
import com.sukhaniuk.models.webMenu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

public class selectCommand {
    private Statement st;
    private ResultSet rs;

    public ArrayList<webMenu> selectWebMenu() {
        ArrayList<webMenu> webMenu = new ArrayList();
        String query = "select * from webmenu order by menusort";
        try (Connection conn = database.connect()) {
            st = conn.createStatement();
            try (ResultSet resultSet = st.executeQuery(query))
            {
                while (rs.next())
                {
                    webMenu.add(new webMenu(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("link"),
                            rs.getInt("menusort")));
                }
                return webMenu;
            }
            catch (SQLException e)
            {
                System.out.println("Не правильный запрос"+e);
            }
        }catch (SQLException e)
        {
            System.out.println("Нету подключения"+e);
        }
        return null;
    }
//    public ArrayList<employees> login(String username, String password) {
//        try (Connection conn = database.connect()) {
//            Statement statement = conn.createStatement();
//            try (ResultSet resultSet = statement.executeQuery("select id, positionsID, cafeID, name, lastname, adress, birthdayDay, elogin, epassword from employees " +
//                    " where elogin='" + username + "' and epassword='" + password + "'")) {
//                if (resultSet.next()) {
//                    Main.client.add(new user(resultSet.getInt("id"),
//                            resultSet.getString("name"),
//                            resultSet.getString("lastname"),
//                            incoming, resultSet.getInt("positionsID")));
//                    ArrayList<employees> empl = new ArrayList<>();
//                    empl.add(new employees(resultSet.getInt("id"), resultSet.getInt("positionsID"), resultSet.getInt("cafeID"),
//                            resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getString("adress"), resultSet.getDate("birthdayDay"),
//                            resultSet.getString("elogin"), resultSet.getString("epassword")));
//                    return empl;
//                } else {
//                    return null;
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//            return null;
//        }
//    }
}
