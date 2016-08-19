package com.shyslav.data;

import com.happycake.storage.CategoryStorage;
import com.happycake.storage.DishStorage;
import com.happycake.storage.NewsStorage;
import com.sukhaniuk.models.*;
import com.sukhaniuk.select.SelectCommand;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 18.08.2016.
 */
public class SiteData extends SelectCommand {
    private final ArrayList<WebMenu> webMenu;
    private final ArrayList<CafeCoordinate> cafeCoordinates;
    private final CategoryStorage categories;
    private final DishStorage dishes;
    private NewsStorage newsList;
    private ArrayList<Reports> repartees;
    private final ArrayList<HotPrice> hotPrices;

    /**
     * Load all data
     * @throws SQLException
     */
    public SiteData() throws SQLException {
        webMenu = selectWebMenu();
        cafeCoordinates = selectCafeCoordinate();
        categories = new CategoryStorage();
        selectCategory().forEach(
                element->categories.add(element)
        );
        dishes = new DishStorage();
        selectdish(0).forEach(
                element->dishes.add(element)
        );
        newsList = new NewsStorage();
        for (News element : selectNews()) {
            newsList.put(element.getId(), element);
        }
        repartees = selectReports();
        hotPrices = selectHotPrice();
    }

    /**
     * action to reload news
     */
    public void reloadNews() {
        try {
            newsList.clear();
            selectNews().forEach(element ->
            {
                newsList.put(element.getId(), element);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * action to reload reports
     */
    public void reloadReported() {
        try {
            repartees = selectReports();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<WebMenu> getWebMenu() {
        return webMenu;
    }

    public ArrayList<CafeCoordinate> getCafeCoordinates() {
        return cafeCoordinates;
    }

    public CategoryStorage getCategories() {
        return categories;
    }

    public DishStorage getDishes() {
        return dishes;
    }

    public NewsStorage getNewsList() {
        return newsList;
    }

    public ArrayList<Reports> getRepartees() {
        return repartees;
    }

    public ArrayList<HotPrice> getHotPrices() {
        return hotPrices;
    }
}
