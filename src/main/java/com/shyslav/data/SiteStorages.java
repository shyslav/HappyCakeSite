package com.shyslav.data;

import com.shyslav.mysql.connectionpool.ConnectionPool;
import com.shyslav.mysql.driver.ConnectionDriver;
import com.shyslav.mysql.driver.DBSpringDriver;
import com.shyslav.mysql.exceptions.DBException;
import com.shyslav.springapp.ApplicationSpringContext;
import com.shyslav.springapp.SpringApplicationException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import storages.*;

/**
 * @author Shyshkin Vladyslav on 29.04.17.
 */
public class SiteStorages {
    private static final Logger log = Logger.getLogger(SiteStorages.class.getName());
    /**
     * Database connection pool
     */
    private ConnectionPool pool;
    /**
     * Web menu storage
     */
    public WebMenuStorage webMenuStorage;
    /**
     * Hot price storage
     */
    public HotPriceStorage hotPriceStorage;
    /**
     * Category storage
     */
    public CategoryStorage categoryStorage;
    /**
     * Dish storage
     */
    public DishStorage dishStorage;
    /**
     * Storage of cafe coordinate
     */
    public CafeCoordinateStorage cafeCoordinate;
    /**
     * Storage of news
     */
    public NewsStorage newsStorage;
    /**
     * Reservation storage
     */
    public ReservationStorage reservationStorage;
    /**
     * pre order storage
     */
    public PreOrderStorage preOrderStorage;
    /**
     * Reports storage
     */
    public ReportsStorage reportsStorage;

    public SiteStorages() {
        ApplicationContext context;
        try {
            context = ApplicationSpringContext.getFromEmbedSource("/etc/start.xml");
            DBSpringDriver driver = (DBSpringDriver) context.getBean("database_driver");
            this.pool = new ConnectionPool(driver);
            open();
        } catch (DBException | SpringApplicationException e) {
            log.trace("Unable to start db pool " + e.getMessage(), e);
            System.exit(-1);
        }
    }

    public void open() throws DBException {
        this.webMenuStorage = new WebMenuStorage(pool);
        this.hotPriceStorage = new HotPriceStorage(pool);
        this.categoryStorage = new CategoryStorage(pool);
        this.dishStorage = new DishStorage(pool);
        this.cafeCoordinate = new CafeCoordinateStorage(pool);
        this.newsStorage = new NewsStorage(pool);
        this.reservationStorage = new ReservationStorage(pool);
        this.preOrderStorage = new PreOrderStorage(pool);
        this.reportsStorage = new ReportsStorage(pool);
    }
}
