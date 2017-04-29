package com.shyslav.data;

import com.shyslav.mysql.connectionpool.ConnectionPool;
import com.shyslav.mysql.driver.ConnectionDriver;
import com.shyslav.mysql.exceptions.DBException;
import com.shyslav.springapp.ApplicationSpringContext;
import com.shyslav.springapp.SpringApplicationException;
import org.springframework.context.ApplicationContext;
import storages.*;

/**
 * @author Shyshkin Vladyslav on 29.04.17.
 */
public class SiteStorages {
    private ConnectionPool pool;
    public WebMenuStorage webMenuStorage;
    public HotPriceStorage hotPriceStorage;
    public CategoryStorage categoryStorage;
    public DishStorage dishStorage;
    public CafeCoordinateStorage cafeCoordinate;
    public NewsStorage newsStorage;

    public SiteStorages() {
        ApplicationContext context;
        try {
            context = ApplicationSpringContext.getFromEmbedSource("/etc/start.xml");
            ConnectionDriver driver = (ConnectionDriver) context.getBean("database_driver");
            this.pool = new ConnectionPool(driver);
            this.webMenuStorage = new WebMenuStorage(pool);
            this.hotPriceStorage = new HotPriceStorage(pool);
            this.categoryStorage = new CategoryStorage(pool);
            this.dishStorage = new DishStorage(pool);
            this.cafeCoordinate = new CafeCoordinateStorage(pool);
            this.newsStorage = new NewsStorage(pool);
        } catch (DBException | SpringApplicationException e) {
            System.out.println("Unable to start db pool");
            System.exit(-1);
        }
    }
}
