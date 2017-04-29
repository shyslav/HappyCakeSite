package com.shyslav.controller;

import com.happycake.GlobalController;
import com.shyslav.data.UserBean;
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
@Controller
@EnableWebMvc
public class JsonControlelr extends GlobalController {
    @RequestMapping(value = "/dish1.json")
    @ResponseBody
    public ArrayList<String> dish1() {
        ArrayList<String> dish = new ArrayList();
        dish.add("Панини");
        dish.add("Чиабатта");
        return dish;
    }

    @RequestMapping(value = "/webmenu.json")
    @ResponseBody
    public ArrayList<WebMenu> webMenu(HttpServletRequest request) {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getWebMenu();
    }
//    @RequestMapping(value = "/cafeCoordinate.json")
//    public @ResponseBody ArrayList<CafeCoordinate> cafeCoordinate(HttpServletRequest request) {
//        UserBean user = getUserInfo(request);
//        return user.getSiteData().getCafeCoordinates();
//    }
//
//    @RequestMapping(value = "/category.json")
//    public @ResponseBody ArrayList<Category> category(HttpServletRequest request) {
//        UserBean user = getUserInfo(request);
//        return user.getSiteData().getCategories();
//    }
//
//    @RequestMapping(value = "/dish.json")
//    public @ResponseBody ArrayList<Dish> dish(HttpServletRequest request) {
//        UserBean user = getUserInfo(request);
//        return user.getSiteData().getDishes();
//    }

}
