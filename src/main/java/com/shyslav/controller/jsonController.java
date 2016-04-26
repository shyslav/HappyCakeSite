package com.shyslav.controller;

import com.sukhaniuk.models.cafeCoordinate;
import com.sukhaniuk.models.*;
import com.sukhaniuk.select.selectCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by shyshkin_vlad on 10.04.16.
 */
@Controller
@EnableWebMvc
public class jsonController {
    private selectCommand selectCommand = new selectCommand();
//    @RequestMapping(value = "/dish.json")
//    public @ResponseBody
//    String dish(){
//        ArrayList<String> dish= new ArrayList();
//        dish.add("Панини");
//        dish.add("Чиабатта");
//        return "hello from STRING JSON";
//    }
    @RequestMapping(value = "/dish1.json")
    public @ResponseBody ArrayList<String> dish1(){
        ArrayList<String> dish= new ArrayList();
        dish.add("Панини");
        dish.add("Чиабатта");
        return dish;
    }
    @RequestMapping(value = "/webmenu.json")
    public @ResponseBody ArrayList<webMenu> webMenu()  {
        ArrayList<webMenu> menu = null;
        try {
            menu = selectCommand.selectWebMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }
    @RequestMapping(value = "/cafeCoordinate.json")
    public @ResponseBody ArrayList<cafeCoordinate> cafeCoordinate() {
        ArrayList<cafeCoordinate> cafeCoordinate = null;
        try {
            cafeCoordinate = selectCommand.selectCafeCoordinate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cafeCoordinate;
    }


    @RequestMapping(value = "/category.json")
    public @ResponseBody ArrayList<category> category() {
        ArrayList<category> category = null;
        try {
            category = selectCommand.selectCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @RequestMapping(value = "/dish.json")
    public @ResponseBody ArrayList<dish> dish() {
        ArrayList<dish> dish = null;
        try {
            dish = selectCommand.selectdish();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dish;
    }

}
