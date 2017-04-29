package com.shyslav.controller;

import com.happycake.GlobalController;
import com.shyslav.data.SiteData;
import com.shyslav.data.UserBean;
import com.shyslav.mysql.exceptions.DBException;
import sitemodels.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author Shyshkin Vladyslav on 10.04.16.
 */
@SuppressWarnings("unused")
@Controller
@EnableWebMvc
public class JsonController extends GlobalController {
    /**
     * Get web menu by json
     *
     * @param request Action request
     * @return web menu
     */
    @RequestMapping(value = "/webmenu.json")
    @ResponseBody
    public ArrayList<WebMenu> webMenu(HttpServletRequest request) {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getWebMenu();
    }

    /**
     * Get cafe coordinate by json
     *
     * @param request Action request
     * @return cafe coordinate
     */
    @RequestMapping(value = "/cafeCoordinate.json")
    @ResponseBody
    public ArrayList<CafeCoordinate> cafeCoordinate(HttpServletRequest request) {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getCafeCoordinates();
    }

    /**
     * Get categories by json
     *
     * @param request Action request
     * @return categories
     */
    @RequestMapping(value = "/category.json")
    @ResponseBody
    public ArrayList<Category> category(HttpServletRequest request) {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getCategories();
    }

    /**
     * Get list of all dish by json
     *
     * @param request Action request
     * @return dishes list
     */
    @RequestMapping(value = "/dish.json")
    @ResponseBody
    public ArrayList<Dish> dish(HttpServletRequest request) {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getDishes();
    }


    /**
     * Get max reservation id
     *
     * @param request Action request
     * @return reservation id
     */
    @RequestMapping(value = "/reservationmaxid.json")
    @ResponseBody
    public int reservationMaxId(HttpServletRequest request) throws DBException {
        UserBean user = getUserInfo(request);
        return SiteData.getStorage().reservationStorage.getMaxID();
    }
}
