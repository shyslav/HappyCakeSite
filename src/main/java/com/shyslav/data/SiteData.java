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
    //    private ArrayList<Reports> repartees;
    private final HotPriceList hotPrices;
    private final CategoriesList categories;
    private final DishesList dishes;
    private final NewsList newsList;


    /**
     * load all site data
     *
     * @throws SQLException sql exception
     */
    public SiteData() throws DBException {
        log.info("initialize SiteData");

        webMenu = storage.webMenuStorage.getAll();
        cafeCoordinates = storage.cafeCoordinate.getAll();

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


    public NewsList getNewsList() {
        return newsList;
    }

    public HotPriceList getHotPrices() {
        return hotPrices;
    }

    public DishesList getDishes() {
        return dishes;
    }

    public static SiteStorages getStorage() {
        return storage;
    }
}
