package com.shyslav.controller;

import com.sukhaniuk.models.*;
import com.sukhaniuk.select.selectCommand;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by shyshkin_vlad on 10.04.16.
 */
@Controller
public class HomeController {
    @RequestMapping(value="index")
    public String home(ModelMap map) throws IOException, JSONException {
        map.addAttribute("webTitle","Главная");
        map.addAttribute("webMenu",headerLoader());
        map.addAttribute("hotPrice", hotPriceLoader());
        map.addAttribute("randCategory", randCategory());
        return "index.jsp";
    }
    @RequestMapping(value="contacts")
    public String contacts(ModelMap map) throws IOException, JSONException {
        map.addAttribute("webTitle","Контакты");
        map.addAttribute("webMenu",headerLoader());
        try {
            map.addAttribute("contacts",selectCommand.selectCafeCoordinate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "contacts.jsp";
    }
    @RequestMapping(value="lalal")
    public String lalal(ModelMap map) throws IOException, JSONException {
        map.addAttribute("webTitle","lol");
        return "index.jsp";
    }
    public static ArrayList<webMenu> headerLoader()
    {
        ArrayList<webMenu> menu = null;
        try {
            menu = selectCommand.selectWebMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }
    public static ArrayList<hotPrice> hotPriceLoader()
    {
        ArrayList<hotPrice> hotPrice = null;
        try {
            hotPrice = selectCommand.selectHotPrice();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotPrice;
    }
    public static ArrayList<category> randCategory()
    {
        ArrayList<category> category = null;
        try {
            category = selectCommand.selectRandCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}
