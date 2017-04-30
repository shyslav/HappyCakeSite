package com.shyslav.data;

import com.happycake.HappyCakeStorage;
import com.shyslav.mysql.exceptions.DBException;
import org.apache.log4j.Logger;
import com.happycake.sitemodels.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 18.08.2016.
 */
@SuppressWarnings("unused")
public class SiteData {
    private static final Logger log = Logger.getLogger(SiteData.class.getName());
    private static final HappyCakeStorage storage = new HappyCakeStorage();

    /**
     * Site web menu
     */
    private ArrayList<WebMenu> webMenu;
    /**
     * Cafe coordinates list
     */
    private final ArrayList<CafeCoordinate> cafeCoordinates;
    /**
     * Hot prices list
     */
    private final HotPriceList hotPrices;
    /**
     * Categories list
     */
    private final CategoriesList categories;
    /**
     * Dishes list
     */
    private final DishesList dishes;
    /**
     * News list
     */
    private final NewsList newsList;
    /**
     * Reports list
     */
    private ArrayList<Reports> reports;


    /**
     * Load all site data
     *
     * @throws SQLException sql exception
     */
    public SiteData() throws DBException {
        log.info("initialize SiteData");

        webMenu = storage.webMenuStorage.getAll();
        cafeCoordinates = storage.cafeCoordinate.getAll();
        reports = storage.reportsStorage.getReportsByVision(true);

        //load hot price
        HotPriceList hotPrices = new HotPriceList();
        storage.hotPriceStorage.getAll().forEach(e -> hotPrices.add((HotPrice) e));
        this.hotPrices = hotPrices;

        //load categories
        CategoriesList categories = new CategoriesList();
        storage.categoryStorage.getAll().forEach(e -> categories.add((Category) e));
        this.categories = categories;

        //load dishes
        DishesList dishes = new DishesList();
        storage.dishStorage.getAll().forEach(e -> dishes.add((Dish) e));
        dishes.loadDiscount(hotPrices);
        this.dishes = dishes;

        //load news list
        NewsList newsList = new NewsList();
        storage.newsStorage.getAll().forEach(e -> newsList.add((News) e));
        this.newsList = newsList;
    }

    public ArrayList<WebMenu> getWebMenu() {
        return webMenu;
    }

    public ArrayList<CafeCoordinate> getCafeCoordinates() {
        return cafeCoordinates;
    }

    public CategoriesList getCategories() {
        return categories;
    }

    public NewsList getNewsList() {
        return newsList;
    }

    public HotPriceList getHotPrices() {
        return hotPrices;
    }

    public DishesList getDishes() {
        return dishes;
    }

    public static HappyCakeStorage getStorage() {
        return storage;
    }

    public ArrayList<Reports> getReports() {
        return reports;
    }
}
