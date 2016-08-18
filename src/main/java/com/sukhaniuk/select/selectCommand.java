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
    private static DatabaseConnection db = new DatabaseConnection();

    /**
     * Получить меню сайта
     * @return
     * @throws SQLException
     */
    protected static ArrayList<webMenu> selectWebMenu() throws SQLException
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
    public static int selectMaxFromReservation()
    {
        String query = "select max(id) from reservation";
        db.getConnection();
        int max = 1;
        try
        {
            db.rs = db.st.executeQuery(query);
            while(db.rs.next())
            {
               max = db.rs.getInt(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        finally
        {
            db.closeConnection();
        }
        return  max;
    }
    /**
     * Получить координаты кафе
     * @return
     * @throws SQLException
     */
    protected static ArrayList<cafeCoordinate> selectCafeCoordinate() throws SQLException
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
                        db.rs.getString("mobilePhone"),
                        db.rs.getString("cafeemail")));
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

    /**
     * Получить категории кафе
     * @return
     * @throws SQLException
     */
    protected static ArrayList<category> selectCategory() throws SQLException
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

    /**
     * Получить блюда в категории
     * @param category
     * @return
     * @throws SQLException
     */
    protected static ArrayList<dish> selectdish(int category) throws SQLException
    {
        ArrayList <dish> result = new ArrayList();
        String query = new String();
        switch (category)
        {
            case 0:
                query = "select id as idfromdish, categoryID, name, description, amount, price, image, readyORnot, (select percent from hotprice where dishID = idfromdish and dateEnd>=curdate() ) as sell from dish";
                break;
            default:
                query = "select id as idfromdish, categoryID, name, description, amount, price, image, readyORnot, (select percent from hotprice where dishID = idfromdish and dateEnd>=curdate() ) as sell from dish  where categoryID = " + category;
        }
        db.getConnection();
        try
        {
            db.rs = db.st.executeQuery(query);
            while(db.rs.next())
            {
                result.add(new dish(
                        db.rs.getInt("idfromdish"),
                        db.rs.getInt("categoryID"),
                        db.rs.getString("name"),
                        db.rs.getString("description"),
                        db.rs.getInt("amount"),
                        db.rs.getDouble("price"),
                        db.rs.getString("image"),
                        db.rs.getString("readyORnot").trim(),
                        db.rs.getString("sell")));
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

    /**
     * Получить Все новости (-1) Получить лучшие 3 новости (-2) Получить носоть по ид (0...id)
     * @return
     * @throws SQLException
     */
    protected static ArrayList<news> selectNews() throws SQLException
    {
        ArrayList <news> result = new ArrayList();
        String query="select * from news";
        db.getConnection();
        try
        {
            db.rs = db.st.executeQuery(query);
            while(db.rs.next())
            {
                result.add(new news(
                        db.rs.getInt("id"),
                        db.rs.getInt("authorID"),
                        db.rs.getString("name"),
                        db.rs.getString("nText"),
                        db.rs.getDate("nDate"),
                        db.rs.getString("tegs"),
                        db.rs.getInt("views"),
                        db.rs.getString("imageLink")));
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

    /**
     * Получить новость по тегу
     * @param tegs
     * @return
     * @throws SQLException
     */
    public static ArrayList<news> selectNewsWithTeg(String tegs) throws SQLException
    {
        ArrayList <news> result = new ArrayList();
        String query = "select * from news where tegs like '%"+tegs+"%'";
        db.getConnection();
        try
        {
            db.rs = db.st.executeQuery(query);
            while(db.rs.next())
            {
                result.add(new news(db.rs.getInt("id"),
                        db.rs.getInt("authorID"),
                        db.rs.getString("name"),
                        db.rs.getString("nText"),
                        db.rs.getDate("nDate"),
                        db.rs.getString("tegs"),
                        db.rs.getInt("views"),
                        db.rs.getString("imageLink")));
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
    //responses

    /**
     * Получить отзывы
     * @return
     * @throws SQLException
     */
    protected static ArrayList<reports> selectReports() throws SQLException
    {
        ArrayList <reports> result = new ArrayList();
        String query = "select * from reports where vision = '+'";
        db.getConnection();
        try
        {
            db.rs = db.st.executeQuery(query);
            while(db.rs.next())
            {
                result.add(new reports(db.rs.getInt("id"),
                        db.rs.getString("author"),
                        db.rs.getString("rText"),
                        db.rs.getDate("rDate"),
                        db.rs.getString("mail"),
                        db.rs.getString("phone"),
                        db.rs.getString("vision")));
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
    /**
     * Получить хотпрайсы
     * @return
     * @throws SQLException
     */
    protected static ArrayList<hotPrice> selectHotPrice() throws SQLException
    {
        ArrayList <hotPrice> result = new ArrayList();
        String query = "select * from hotprice order by  dateStart desc, dateEnd DESC";
        db.getConnection();
        try
        {
            db.rs = db.st.executeQuery(query);
            while(db.rs.next())
            {
                result.add(new hotPrice(db.rs.getInt("id"),
                        db.rs.getInt("dishId"),
                        db.rs.getInt("percent"),
                        db.rs.getString("description"),
                        db.rs.getDate("dateStart"),
                        db.rs.getDate("dateEnd")));
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
    /**
     * Получить 3 рандомные категории
     * @return
     * @throws SQLException
     */
    public static ArrayList<category> selectRandCategory() throws SQLException
    {
        ArrayList <category> result = new ArrayList();
        String query = "SELECT * FROM category ORDER BY RAND() LIMIT 3 ";
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
}
