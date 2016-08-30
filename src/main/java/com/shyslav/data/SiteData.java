package com.shyslav.data;


import database.select.SelectCommand;
import org.apache.log4j.Logger;
import sitemodels.*;
import sitestorages.*;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Created by Shyshkin Vladyslav on 18.08.2016.
 */
@SuppressWarnings("unused")
public class SiteData extends SelectCommand {
    private static final Logger log = Logger.getLogger(SiteData.class.getName());

    private final ArrayList<WebMenu> webMenu;
    private final ArrayList<CafeCoordinate> cafeCoordinates;
    private final CategoryStorage categories;
    private final DishStorage dishes;
    private NewsStorage newsList;
    private ArrayList<Reports> repartees;
    private final ArrayList<HotPrice> hotPrices;

    /**
     * load all site data
     * @throws SQLException sql exseption
     */
    public SiteData() throws SQLException {
        log.info("initialize SiteData");
        webMenu = selectWebMenu();
        cafeCoordinates = selectCafeCoordinate();
        categories = new CategoryStorage();
        selectCategory().forEach(
                element->categories.add(element)
        );
        dishes = new DishStorage();
        selectdish().forEach(
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
        newsList.clear();
        selectNews().forEach(element ->
                newsList.put(element.getId(), element));
    }

    /**
     * action to reload reports
     */
    public void reloadReported() {
        repartees = selectReports();
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
