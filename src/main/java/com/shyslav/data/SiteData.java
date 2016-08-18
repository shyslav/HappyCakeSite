package com.shyslav.data;

import com.happycake.storage.DishStorage;
import com.happycake.storage.NewsStorage;
import com.sukhaniuk.models.*;
import com.sukhaniuk.select.selectCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Shyshkin Vladyslav on 18.08.2016.
 */
public class SiteData extends selectCommand {
    private final ArrayList<webMenu> webMenu;
    private final ArrayList<cafeCoordinate> cafeCoordinates;
    private final ArrayList<category> categories;
    private final DishStorage dishes;
    private NewsStorage newsList;
    private ArrayList<reports> repartees;
    private final ArrayList<hotPrice> hotPrices;

    public SiteData() throws SQLException {
        webMenu = selectWebMenu();
        cafeCoordinates = selectCafeCoordinate();
        categories = selectCategory();
        dishes = new DishStorage();
        selectdish(0).forEach(
                element->dishes.add(element)
        );
        newsList = new NewsStorage();
        for (news element : selectNews()) {
            newsList.put(element.getId(), element);
        }
        repartees = selectReports();
        hotPrices = selectHotPrice();
    }

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

    public void reloadReported() {
        try {
            repartees = selectReports();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<webMenu> getWebMenu() {
        return webMenu;
    }

    public ArrayList<cafeCoordinate> getCafeCoordinates() {
        return cafeCoordinates;
    }

    public ArrayList<category> getCategories() {
        return categories;
    }

    public DishStorage getDishes() {
        return dishes;
    }

    public NewsStorage getNewsList() {
        return newsList;
    }

    public ArrayList<reports> getRepartees() {
        return repartees;
    }

    public ArrayList<hotPrice> getHotPrices() {
        return hotPrices;
    }
}
