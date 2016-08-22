package com.sukhaniuk.select;

import com.shyslav.util.DatabaseConnection;
import com.sukhaniuk.models.*;
import org.apache.commons.io.IOUtils;

import java.sql.SQLException;
import java.util.ArrayList;

public class SelectCommand {
    private static DatabaseConnection db = new DatabaseConnection();

    /**
     * Получить меню сайта
     *
     * @return
     * @throws SQLException
     */
    protected ArrayList<WebMenu> selectWebMenu() throws SQLException {
        ArrayList<WebMenu> result = new ArrayList();
        String query = "select * from webmenu order by menusort";
        db.openConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new WebMenu(
                        db.rs.getInt("id"),
                        db.rs.getString("name"),
                        db.rs.getString("link"),
                        db.rs.getInt("menusort")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }

    public static int selectMaxFromReservation() {
        String query = "select max(id) from reservation";
        db.openConnection();
        int max = 1;
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                max = db.rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
        }
        return max;
    }

    /**
     * Получить координаты кафе
     *
     * @return
     * @throws SQLException
     */
    protected ArrayList<CafeCoordinate> selectCafeCoordinate() throws SQLException {
        ArrayList<CafeCoordinate> result = new ArrayList();
        String query = "select * from cafecoordinate";
        db.openConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new CafeCoordinate(
                        db.rs.getInt("id"),
                        db.rs.getString("adress"),
                        db.rs.getString("mobilePhone"),
                        db.rs.getString("cafeemail")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }

    /**
     * Получить категории кафе
     *
     * @return
     * @throws SQLException
     */
    protected ArrayList<Category> selectCategory() throws SQLException {
        ArrayList<Category> result = new ArrayList();
        String query = "select * from category";
        db.openConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new Category(
                        db.rs.getInt("id"),
                        db.rs.getString("name"),
                        db.rs.getString("description"),
                        IOUtils.toByteArray(db.rs.getBinaryStream("image"))));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }

    /**
     * Получить блюда в категории
     *
     * @param category
     * @return
     * @throws SQLException
     */
    protected ArrayList<Dish> selectdish(int category) throws SQLException {
        ArrayList<Dish> result = new ArrayList();
        String query = new String();
        switch (category) {
            case 0:
                query = "select id as idfromdish, categoryID, name, description, amount, price, image, readyORnot, (select percent from hotprice where dishID = idfromdish and dateEnd>=curdate() ) as sell from dish";
                break;
            default:
                query = "select id as idfromdish, categoryID, name, description, amount, price, image, readyORnot, (select percent from hotprice where dishID = idfromdish and dateEnd>=curdate() ) as sell from dish  where categoryID = " + category;
        }
        db.openConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new Dish(
                        db.rs.getInt("idfromdish"),
                        db.rs.getInt("categoryID"),
                        db.rs.getString("name"),
                        db.rs.getString("description"),
                        db.rs.getInt("amount"),
                        db.rs.getDouble("price"),
                        IOUtils.toByteArray(db.rs.getBinaryStream("image")),
                        db.rs.getString("readyORnot").trim(),
                        db.rs.getString("sell")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }

    /**
     * Получить Все новости (-1) Получить лучшие 3 новости (-2) Получить носоть по ид (0...id)
     *
     * @return
     * @throws SQLException
     */
    protected ArrayList<News> selectNews() throws SQLException {
        ArrayList<News> result = new ArrayList();
        String query = "select * from news";
        db.openConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new News(
                        db.rs.getInt("id"),
                        db.rs.getInt("authorID"),
                        db.rs.getString("name"),
                        db.rs.getString("nText"),
                        db.rs.getDate("nDate"),
                        db.rs.getString("tegs"),
                        db.rs.getInt("views"),
                        db.rs.getString("imageLink")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }

    /**
     * Получить отзывы
     *
     * @return
     * @throws SQLException
     */
    protected ArrayList<Reports> selectReports() throws SQLException {
        ArrayList<Reports> result = new ArrayList();
        String query = "select * from reports where vision = '+'";
        db.openConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new Reports(db.rs.getInt("id"),
                        db.rs.getString("author"),
                        db.rs.getString("rText"),
                        db.rs.getDate("rDate"),
                        db.rs.getString("mail"),
                        db.rs.getString("phone"),
                        db.rs.getString("vision")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }

    /**
     * Получить хотпрайсы
     *
     * @return
     * @throws SQLException
     */
    protected ArrayList<HotPrice> selectHotPrice() throws SQLException {
        ArrayList<HotPrice> result = new ArrayList();
        String query = "select * from hotprice order by  dateStart desc, dateEnd DESC";
        db.openConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new HotPrice(db.rs.getInt("id"),
                        db.rs.getInt("dishId"),
                        db.rs.getInt("percent"),
                        db.rs.getString("description"),
                        db.rs.getDate("dateStart"),
                        db.rs.getDate("dateEnd")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            db.closeConnection();
            db.rs.close();
            db.st.close();
        }
        return result;
    }
}
