package com.shyslav.data;

import com.shyslav.mysql.exceptions.DBException;
import org.apache.log4j.Logger;
import sitemodels.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 18.08.2016.
 */
@SuppressWarnings("unused")
public class SiteData {
    private static final Logger log = Logger.getLogger(SiteData.class.getName());
    private static final SiteStorages storage = new SiteStorages();

    private ArrayList<WebMenu> webMenu;
    private final ArrayList<CafeCoordinate> cafeCoordinates;
    //    private final CategoryStorage categories;
//    private final DishStorage dishes;
//    private NewsStorage newsList;
//    private ArrayList<Reports> repartees;
    private final ArrayList<HotPrice> hotPrices;
    private final CategoriesList categories;
    private final DishesList dishes;

    /**
     * load all site data
     *
     * @throws SQLException sql exseption
     */
    public SiteData() throws DBException {
        log.info("initialize SiteData");

        webMenu = storage.webMenuStorage.getAll();
        hotPrices = storage.hotPriceStorage.getAll();
        cafeCoordinates = storage.cafeCoordinate.getAll();

        //load categories
        CategoriesList categories = new CategoriesList();
        storage.categoryStorage.getAll().forEach(e -> categories.add((Category) e));
        this.categories = categories;

        //load dishes
        DishesList dishes = new DishesList();
        storage.dishStorage.getAll().forEach(e -> dishes.add((Dish) e));
        this.dishes = dishes;

//        cafeCoordinates = selectCafeCoordinate();
//        categories = new CategoryStorage();
//        selectCategory().forEach(
//                categories::add
//        );
//        dishes = new DishStorage();
//        selectdish().forEach(
//                dishes::add
//        );
//        newsList = new NewsStorage();
//        for (News element : selectNews()) {
//            newsList.put(element.getId(), element);
//        }
//        repartees = selectReports();
//        hotPrices = selectHotPrice();
    }

    /**
     * action to reload news
     */
    public void reloadNews() {
//        newsList.clear();
//        selectNews().forEach(element ->
//                newsList.put(element.getId(), element));
    }

    /**
     * action to reload reports
     */
//    public void reloadReported() {
//        repartees = selectReports();
//    }
    public ArrayList<WebMenu> getWebMenu() {
        return webMenu;
    }

    //
    public ArrayList<CafeCoordinate> getCafeCoordinates() {
        return cafeCoordinates;
    }
//
    public CategoriesList getCategories() {
        return categories;
    }
//
//    public DishStorage getDishes() {
//        return dishes;
//    }
//
//    public NewsStorage getNewsList() {
//        return newsList;
//    }
//
//    public ArrayList<Reports> getRepartees() {
//        return repartees;
//    }
//

    public ArrayList<HotPrice> getHotPrices() {
        return hotPrices;
    }

    public DishesList getDishes() {
        return dishes;
    }
}
