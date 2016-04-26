package com.sukhaniuk.select;

import com.shyslav.util.DatabaseConnection;
import com.sukhaniuk.models.cafeCoordinate;
import com.sukhaniuk.models.*;
import com.sukhaniuk.models.webMenu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

public class selectCommand {
    private DatabaseConnection db = new DatabaseConnection();

    public ArrayList<webMenu> selectWebMenu() throws SQLException
    {
        ArrayList <webMenu> result = new ArrayList();
        String query = "select * from webmenu order by menusort";
        db.getConnection();
        try
        {
            db.rs = db.st.executeQuery(query);
            while(db.rs.next())
            {
                result.add(new webMenu(
                        db.rs.getInt("id"),
                        db.rs.getString("name"),
                        db.rs.getString("link"),
                        db.rs.getInt("menusort")));
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        finally
        {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }
    public ArrayList<cafeCoordinate> selectCafeCoordinate() throws SQLException
    {
        ArrayList <cafeCoordinate> result = new ArrayList();
        String query = "select * from cafecoordinate";
        db.getConnection();
        try
        {
            db.rs = db.st.executeQuery(query);
            while(db.rs.next())
            {
                result.add(new cafeCoordinate(
                        db.rs.getInt("id"),
                        db.rs.getString("adress"),
                        db.rs.getString("mobilePhone")));
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        finally
        {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }

    public ArrayList<category> selectCategory() throws SQLException
    {
        ArrayList <category> result = new ArrayList();
        String query = "select * from category";
        db.getConnection();
        try
        {
            db.rs = db.st.executeQuery(query);
            while(db.rs.next())
            {
                result.add(new category(
                        db.rs.getInt("id"),
                        db.rs.getString("name"),
                        db.rs.getString("description"),
                        db.rs.getString("image")));
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        finally
        {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }

    public ArrayList<dish> selectdish() throws SQLException
    {
        ArrayList <dish> result = new ArrayList();
        String query = "select * from dish";
        db.getConnection();
        try
        {
            db.rs = db.st.executeQuery(query);
            while(db.rs.next())
            {
                result.add(new dish(
                        db.rs.getInt("id"),
                        db.rs.getInt("categoryID"),
                        db.rs.getString("name"),
                        db.rs.getString("description"),
                        db.rs.getInt("amount"),
                        db.rs.getDouble("price"),
                        db.rs.getString("image"),
                        db.rs.getBoolean("readyORnot")));
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        finally
        {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }
}
