package com.shyslav.controller;

import com.happycake.GlobalController;
import com.shyslav.data.UserBean;
import com.sukhaniuk.models.cafeCoordinate;
import com.sukhaniuk.models.*;
import com.sukhaniuk.select.selectCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by shyshkin_vlad on 10.04.16.
 */
@Controller
@EnableWebMvc
public class jsonController extends GlobalController {
    private selectCommand selectCommand = new selectCommand();
    @RequestMapping(value = "/dish1.json")
    public @ResponseBody ArrayList<String> dish1(){
        ArrayList<String> dish= new ArrayList();
        dish.add("Панини");
        dish.add("Чиабатта");
        return dish;
    }
    @RequestMapping(value = "/webmenu.json")
    public @ResponseBody ArrayList<webMenu> webMenu(HttpServletRequest request)  {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getWebMenu();
    }
    @RequestMapping(value = "/cafeCoordinate.json")
    public @ResponseBody ArrayList<cafeCoordinate> cafeCoordinate(HttpServletRequest request) {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getCafeCoordinates();
    }


    @RequestMapping(value = "/category.json")
    public @ResponseBody ArrayList<category> category(HttpServletRequest request) {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getCategories();
    }

    @RequestMapping(value = "/dish.json")
    public @ResponseBody ArrayList<dish> dish(HttpServletRequest request) {
        UserBean user = getUserInfo(request);
        return user.getSiteData().getDishes();
    }

}
