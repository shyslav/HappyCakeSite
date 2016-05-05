package com.sukhaniuk.controller;

import com.shyslav.controller.HomeController;
import com.sukhaniuk.models.category;
import com.sukhaniuk.select.selectCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 04.05.2016.
 */
@Controller
public class MenuController {
    /**
     * @param map
     * @param request
     * @param redirAtr
     * @return Страница меню
     */
    @RequestMapping(value = "/menu")
    public String menu(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr)
    {
        map.addAttribute("webTitle","Категории");
        map.addAttribute("webMenu", HomeController.headerLoader());
        map.addAttribute("category",category());
        return "menu.jsp";
    }

    /**
     * @param id - ид категории
     * @param request
     * @param redirAtr
     * @return - страница блюд в данной категории
     */
    @RequestMapping(value="/category/{id}")
    public String dish(@PathVariable("id") String id,ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr)
    {
        map.addAttribute("webTitle","Блюда");
        map.addAttribute("webMenu", HomeController.headerLoader());
        try {
            map.addAttribute("dish",selectCommand.selectdish(Integer.parseInt(id)));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "/dish.jsp";
    }

    public static ArrayList<category> category()
    {
        ArrayList<category> categories = null;
        try {
            categories = selectCommand.selectCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
