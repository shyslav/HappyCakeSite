package com.happycake;

import com.shyslav.data.UserBean;
import com.shyslav.mysql.exceptions.DBException;
import com.shyslav.utils.LazyRandom;
import org.apache.log4j.Logger;
import sitemodels.Category;
import sitemodels.HotPrice;
import sitemodels.WebMenu;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 18.08.2016.
 */
public class GlobalController {
    private static final Logger log = Logger.getLogger(GlobalController.class.getName());

    /**
     * Get user data
     *
     * @param req Servlet request
     * @return user data
     */
    protected UserBean getUserInfo(HttpServletRequest req) {
        if (req.getSession().getAttribute("userBean") == null) {
            try {
                log.info("get user bean data");
                UserBean userBean = new UserBean();
                req.getSession().setAttribute("userBean", userBean);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
        return (UserBean) req.getSession().getAttribute("userBean");
    }

    /**
     * Load page header
     *
     * @param request Servlet request
     * @return web menu
     */
    protected ArrayList<WebMenu> headerLoader(HttpServletRequest request) {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getWebMenu();
    }

    /**
     * Load hot price
     *
     * @param request Servlet request
     * @return hot prices list
     */
    protected ArrayList<HotPrice> hotPriceLoader(HttpServletRequest request) {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getHotPrices();
    }

    /**
     * Load random category
     *
     * @param request Servlet request
     * @return random categories
     */
    protected ArrayList<Category> randCategory(HttpServletRequest request, int amountCategories) {
        UserBean user = getUserInfo(request);
        ArrayList<Category> categories = user.getSiteData().getCategories();
        int[] ids = LazyRandom.getUniqueInt(0, categories.size() - 1, amountCategories);

        ArrayList<Category> newArray = new ArrayList<>();
        for (int id : ids) {
            newArray.add(categories.get(id));
        }
        return newArray;
    }
}
