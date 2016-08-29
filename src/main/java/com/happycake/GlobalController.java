package com.happycake;

import com.shyslav.data.UserBean;
import org.apache.log4j.Logger;
import sitemodels.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 18.08.2016.
 */
public class GlobalController {
    private static final Logger log = Logger.getLogger(GlobalController.class.getName());
    /**
     * Get user data
     * @param req Servlet request
     * @return user data
     */
    protected UserBean getUserInfo(HttpServletRequest req){
        if(req.getSession().getAttribute("userBean") == null){
            try {
                log.info("get user bean data");
                UserBean userBean = new UserBean();
                req.getSession().setAttribute("userBean",userBean);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (UserBean) req.getSession().getAttribute("userBean");
    }

    /**
     * Load page header
     * @param request
     * @return
     */
    protected ArrayList<WebMenu> headerLoader (HttpServletRequest request)
    {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getWebMenu();
    }

    /**
     * Load hot price
     * @param request
     * @return
     */
    protected ArrayList<HotPrice> hotPriceLoader(HttpServletRequest request)
    {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getHotPrices();
    }

    /**
     * Load random category
     * @param request
     * @return
     */
    protected ArrayList<Category> randCategory(HttpServletRequest request)
    {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getCategories().getRand();
    }
}
